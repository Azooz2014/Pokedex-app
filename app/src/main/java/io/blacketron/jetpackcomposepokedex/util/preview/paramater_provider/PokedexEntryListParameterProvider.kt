package io.blacketron.jetpackcomposepokedex.util.preview.paramater_provider

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import io.blacketron.jetpackcomposepokedex.data.model.Pokedex

class PokedexEntryListParameterProvider : PreviewParameterProvider<Pokedex> {
    override val values = sequenceOf(
        Pokedex(pokemonName = "ditto",
            imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/132.png",
            pokemonNumber = 132)
    )

}