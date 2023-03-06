package io.blacketron.jetpackcomposepokedex.data.local.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.blacketron.jetpackcomposepokedex.data.local.model.Pokemon
import io.blacketron.jetpackcomposepokedex.util.Constants.POKEMON_TABLE_NAME

@Dao
interface PokemonDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPokemons(pokemons: List<Pokemon>)

    @Query("SELECT * FROM $POKEMON_TABLE_NAME WHERE id = :pokemonId")
    suspend fun getPokemon(pokemonId: Int): Pokemon

    @Query("SELECT * FROM $POKEMON_TABLE_NAME ORDER BY id ASC")
    fun getAllPokemons(): PagingSource<Int, Pokemon>

    @Query("DELETE FROM $POKEMON_TABLE_NAME")
    suspend fun deleteAllPokemons()

}