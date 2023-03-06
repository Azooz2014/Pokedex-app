package io.blacketron.jetpackcomposepokedex.data.remote

import io.blacketron.jetpackcomposepokedex.data.remote.response.Pokemon
import io.blacketron.jetpackcomposepokedex.data.remote.response.PokemonList
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokeApi {

    @GET("pokemon")
    suspend fun getPokemonList(
        @Query("limit") limit: Int,
        @Query("offset") offset: Int
    ) : PokemonList

    @GET("pokemon/{pokemonId}")
    suspend fun getPokemonInfo(
        @Path("pokemonId") id: Int
    ): Pokemon
}