package io.blacketron.jetpackcomposepokedex.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import io.blacketron.jetpackcomposepokedex.data.remote.response.*
import io.blacketron.jetpackcomposepokedex.util.Constants.POKEMON_TABLE_NAME

@Entity(tableName = POKEMON_TABLE_NAME)
data class Pokemon(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val name: String,
    val abilities: List<Ability>,
    val baseExperience: Int,
    val forms: List<Form>,
    val gameIndices: List<GameIndex>,
    val height: Int,
    val heldItems: List<Any>,
    val isDefault: Boolean,
    val locationAreaEncounters: String,
    val moves: List<Move>,
    val order: Int,
    val pastTypes: List<Any>,
    val species: Species,
    val sprites: Sprites,
    val stats: List<Stat>,
    val types: List<Type>,
    val weight: Int
)
