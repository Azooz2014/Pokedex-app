package io.blacketron.jetpackcomposepokedex.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import io.blacketron.jetpackcomposepokedex.util.Constants.POKEMON_REMOTE_KEYS_TABLE_NAME

@Entity(tableName = POKEMON_REMOTE_KEYS_TABLE_NAME)
data class PokeRemoteKeys(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val prevKey: Int?,
    val nextKey: Int?,
    val name: String,
    val pokemonUrl: String,
    val lastUpdate: Long
)
