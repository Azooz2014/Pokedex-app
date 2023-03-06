package io.blacketron.jetpackcomposepokedex.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import dagger.hilt.android.scopes.ActivityScoped
import io.blacketron.jetpackcomposepokedex.data.local.database.PokemonDatabase
import io.blacketron.jetpackcomposepokedex.data.local.mediator.PokeRemoteMediator
import io.blacketron.jetpackcomposepokedex.data.local.model.PokeRemoteKeys
import io.blacketron.jetpackcomposepokedex.data.remote.PokeApi
import io.blacketron.jetpackcomposepokedex.data.local.model.Pokemon
import io.blacketron.jetpackcomposepokedex.data.local.model.relationship.PokedexWithPokemons
import io.blacketron.jetpackcomposepokedex.util.Constants.PAGE_LIMIT
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@ExperimentalPagingApi
@ActivityScoped
class PokeRepositroyImp @Inject constructor(
    private val api: PokeApi,
    private val database: PokemonDatabase
) : PokeRepository {

    val pokemonDAO = database.getPokemonDao()
    val pokeRemoteKeysDAO = database.getPokeRemoteKeysDao()

    //TODO: Finish implementation of caching.
    override fun getPokemons(): Flow<PagingData<PokedexWithPokemons>> {

        val pagingSourceFactory = { pokeRemoteKeysDAO.getPokedexWithPokemons() }

        return Pager(
            config = PagingConfig(pageSize = PAGE_LIMIT),
            remoteMediator = PokeRemoteMediator(
                api = api,
                database = database
            ),
            pagingSourceFactory = pagingSourceFactory
        ).flow
    }

    override suspend fun getPokemon(pokemonId: Int): Pokemon {
        return pokemonDAO.getPokemon(pokemonId)
    }

    /*suspend fun getPokemonList(limit: Int, offset: Int): Resource<PokemonList>{

        val response = try {
            api.getPokemonList(limit, offset)

            } catch (e: Exception) {

                Timber.e(e)
                return Resource.Error(message = "An unknown error occurred")
            }

        return Resource.Success(response)
    }

    suspend fun getPokemonInfo(pokemonName: String): Resource<Pokemon>{

        val response = try {
            api.getPokemonInfo(pokemonName)

        } catch (e: Exception) {

            Timber.e(e)
            return Resource.Error(message = "An unknown error occurred")
        }

        return Resource.Success(response)
    }*/

    /*suspend fun  cachePokemonsList(pokemons: List<Result>) = dao.insertPokedex(pokemons)

    suspend fun cachePokemon(pokemon: Pokemon) = dao.insertPokemon(pokemon)

    suspend fun cachePokemons(pokemons: List<Pokemon>) = dao.insertPokemons(pokemons)

    suspend fun getCachedPokemons() = dao.getPokemons()

    suspend fun getCachedPokemon(pokemonName: String) = dao.getPokemon(pokemonName)

    suspend fun isAppFirstTimeRun() = dao.getPokemons().isEmpty()*/
}