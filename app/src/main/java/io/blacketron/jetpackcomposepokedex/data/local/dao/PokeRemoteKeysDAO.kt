package io.blacketron.jetpackcomposepokedex.data.local.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import io.blacketron.jetpackcomposepokedex.data.local.model.PokeRemoteKeys
import io.blacketron.jetpackcomposepokedex.data.local.model.relationship.PokedexWithPokemons
import io.blacketron.jetpackcomposepokedex.util.Constants.POKEMON_REMOTE_KEYS_TABLE_NAME
import kotlinx.coroutines.flow.Flow

@Dao
interface PokeRemoteKeysDAO {

    @Query("SELECT * FROM $POKEMON_REMOTE_KEYS_TABLE_NAME WHERE id = :pokemonId" )
    suspend fun getRemoteKey(pokemonId: Int): PokeRemoteKeys?

    @Query("SELECT * FROM $POKEMON_REMOTE_KEYS_TABLE_NAME ORDER BY id ASC")
    fun getRemoteKeys(): PagingSource<Int, PokeRemoteKeys>

    @Transaction
    @Query("SELECT * FROM $POKEMON_REMOTE_KEYS_TABLE_NAME ORDER BY id ASC")
    fun getPokedexWithPokemons(): PagingSource<Int, PokedexWithPokemons>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRemoteKeys(remoteKeys: List<PokeRemoteKeys>)

    @Query("DELETE FROM $POKEMON_REMOTE_KEYS_TABLE_NAME")
    suspend fun deleteAllRemoteKeys()
}