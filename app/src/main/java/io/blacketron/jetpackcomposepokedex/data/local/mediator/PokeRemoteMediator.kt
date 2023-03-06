package io.blacketron.jetpackcomposepokedex.data.local.mediator

import android.util.Log
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import io.blacketron.jetpackcomposepokedex.data.local.database.PokemonDatabase
import io.blacketron.jetpackcomposepokedex.data.local.model.PokeRemoteKeys
import io.blacketron.jetpackcomposepokedex.data.local.model.Pokemon
import io.blacketron.jetpackcomposepokedex.data.local.model.relationship.PokedexWithPokemons
import io.blacketron.jetpackcomposepokedex.data.remote.PokeApi
import io.blacketron.jetpackcomposepokedex.util.Constants.CACHE_VALIDITY_IN_MINUETS
import io.blacketron.jetpackcomposepokedex.util.Constants.PAGE_LIMIT
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

@ExperimentalPagingApi
class PokeRemoteMediator @Inject constructor(
    private val api: PokeApi,
    private val database: PokemonDatabase
) : RemoteMediator<Int, PokedexWithPokemons>() {

    private val pokemonDao = database.getPokemonDao()
    private val remoteKeysDao = database.getPokeRemoteKeysDao()

    override suspend fun initialize(): InitializeAction {

        val currentTime = System.currentTimeMillis()
        val lastUpdatedTime = remoteKeysDao.getRemoteKey(pokemonId = 1)?.lastUpdate ?: 0L
        val cacheValidity = CACHE_VALIDITY_IN_MINUETS

        /*Get difference of time in milliseconds then convert it to minuets
        * 1 second = 1000 millisecond
        * 1 minute = 60 seconds
        * formula: calculated time in milliseconds / 1000 / 60 */
        val diffInMinuets = (currentTime - lastUpdatedTime) / 1000 / 60

        return if (diffInMinuets.toInt() <= cacheValidity) {
            Log.d("RemoteMediator", "UP TO DATE!")
            InitializeAction.SKIP_INITIAL_REFRESH
        } else {
            Log.d("RemoteMediator", "REFRESHED!")
            InitializeAction.LAUNCH_INITIAL_REFRESH
        }

    }

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, PokedexWithPokemons>
    ): MediatorResult {

        return try {

            val offset = when (loadType) {
                LoadType.REFRESH -> {
                    val remoteKeys = getRemoteKeysClosestToCurrentPosition(state)
                    remoteKeys?.nextKey?.minus(PAGE_LIMIT) ?: 0
                }
                LoadType.PREPEND -> {
                    val remoteKeys = getRemoteKeysForFirstItem(state)
                    val prevKey = remoteKeys?.prevKey
                        ?: return MediatorResult.Success(endOfPaginationReached = remoteKeys != null)
                    prevKey
                }
                LoadType.APPEND -> {
                    val remoteKeys = getRemoteKeysForLastItem(state)
                    val nextKey = remoteKeys?.nextKey
                        ?: return MediatorResult.Success(endOfPaginationReached = remoteKeys != null)
                    nextKey
                }
            }

            val pokemonResponseList = api.getPokemonList(limit = PAGE_LIMIT, offset = offset)


            if (pokemonResponseList.results.isNotEmpty()) {
                database.withTransaction {
                    if (loadType === LoadType.REFRESH) {
                        pokemonDao.deleteAllPokemons()
                        remoteKeysDao.deleteAllRemoteKeys()
                    }

                    val prevKey = pokemonResponseList.previous?.substringAfter("=")
                        ?.substringBefore("&")?.toInt()
                    val nextKey = pokemonResponseList.next?.substringAfter("=")
                        ?.substringBefore("&")?.toInt()

                    val remoteKeys = pokemonResponseList.results.map { pokemon ->
                        PokeRemoteKeys(
                            id = pokemon.url.substringBeforeLast("/")
                                .substringAfterLast("/").toInt(),
                            prevKey = prevKey,
                            nextKey = nextKey,
                            name = pokemon.name,
                            pokemonUrl = pokemon.url,
                            lastUpdate = System.currentTimeMillis()
                        )
                    }

                    val pokemons = pokemonResponseList.results.map { pokemon ->
                        val id = pokemon.url.substringBeforeLast("/")
                            .substringAfterLast("/").toInt()
                        val response = api.getPokemonInfo(id)

                        Pokemon(
                            id = response.id,
                            name = response.name,
                            abilities = response.abilities,
                            baseExperience = response.baseExperience,
                            forms = response.forms,
                            gameIndices = response.gameIndices,
                            height = response.height,
                            heldItems = response.heldItems,
                            isDefault = response.isDefault,
                            locationAreaEncounters = response.locationAreaEncounters,
                            moves = response.moves,
                            order = response.order,
                            pastTypes = response.pastTypes,
                            species = response.species,
                            sprites = response.sprites,
                            stats = response.stats,
                            types = response.types,
                            weight = response.weight

                        )
                    }

                    remoteKeysDao.insertRemoteKeys(remoteKeys)
                    pokemonDao.insertPokemons(pokemons)
                }
            }

            MediatorResult.Success(endOfPaginationReached = pokemonResponseList.next == null)


        } catch (exception: Exception) {
            return MediatorResult.Error(exception)
        } catch (ioException: IOException) {
            return MediatorResult.Error(ioException)
        } catch (httpException: HttpException) {
            return MediatorResult.Error(httpException)
        }
    }

    private suspend fun getRemoteKeysClosestToCurrentPosition(
        state: PagingState<Int, PokedexWithPokemons>
    ): PokeRemoteKeys? {

        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.pokedex?.id?.let { id ->
                remoteKeysDao.getRemoteKey(id)
            }
        }
    }

    private suspend fun getRemoteKeysForFirstItem(
        state: PagingState<Int, PokedexWithPokemons>
    ): PokeRemoteKeys? {

        return state.pages.firstOrNull { page ->
            page.data.isNotEmpty()
        }?.data?.firstOrNull()?.let { pokemon ->
            remoteKeysDao.getRemoteKey(pokemon.pokedex.id)
        }
    }

    private suspend fun getRemoteKeysForLastItem(
        state: PagingState<Int, PokedexWithPokemons>
    ): PokeRemoteKeys? {

        return state.pages.lastOrNull { page ->
            page.data.isNotEmpty()
        }?.data?.lastOrNull()?.let { pokemon ->
            remoteKeysDao.getRemoteKey(pokemon.pokedex.id)
        }
    }
}