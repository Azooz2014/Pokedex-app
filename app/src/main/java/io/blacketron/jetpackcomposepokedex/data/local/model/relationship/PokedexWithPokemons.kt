package io.blacketron.jetpackcomposepokedex.data.local.model.relationship

import androidx.room.Embedded
import androidx.room.Relation
import io.blacketron.jetpackcomposepokedex.data.local.model.PokeRemoteKeys
import io.blacketron.jetpackcomposepokedex.data.local.model.Pokemon

data class PokedexWithPokemons(
    @Embedded
    val pokedex: PokeRemoteKeys,
    @Relation(
        parentColumn = "id",
        entityColumn = "id"
    )
    val pokemons: List<Pokemon>
)
