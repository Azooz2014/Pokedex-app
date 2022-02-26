package io.blacketron.jetpackcomposepokedex.data.repository

import dagger.hilt.android.scopes.ActivityScoped
import io.blacketron.jetpackcomposepokedex.data.remote.PokeApi
import io.blacketron.jetpackcomposepokedex.data.remote.response.Pokemon
import io.blacketron.jetpackcomposepokedex.data.remote.response.PokemonList
import io.blacketron.jetpackcomposepokedex.util.Resource
import timber.log.Timber
import javax.inject.Inject

@ActivityScoped
class PokeRepositroy @Inject constructor(
    private val api: PokeApi
) {

    suspend fun getPokemonList(limit: Int, offset: Int): Resource<PokemonList>{

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
    }
}