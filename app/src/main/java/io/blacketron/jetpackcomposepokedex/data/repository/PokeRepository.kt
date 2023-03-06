package io.blacketron.jetpackcomposepokedex.data.repository

import androidx.paging.PagingData
import io.blacketron.jetpackcomposepokedex.data.local.model.PokeRemoteKeys
import io.blacketron.jetpackcomposepokedex.data.local.model.Pokemon
import io.blacketron.jetpackcomposepokedex.data.local.model.relationship.PokedexWithPokemons
import kotlinx.coroutines.flow.Flow

interface PokeRepository {

    fun getPokemons(): Flow<PagingData<PokedexWithPokemons>>
    //suspend fun getPokemonList(): Flow<PagingData<PokeRemoteKeys>>
    suspend fun getPokemon(pokemonId: Int): Pokemon
}