package io.blacketron.jetpackcomposepokedex.util.preview

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import io.blacketron.jetpackcomposepokedex.data.model.Pokedex
import io.blacketron.jetpackcomposepokedex.ui.components.PokedexCard
import io.blacketron.jetpackcomposepokedex.util.preview.paramater_provider.PokedexEntryListParameterProvider

@Preview("Pokemon Card")
@Composable
fun PokemonCardPreview(
        @PreviewParameter(PokedexEntryListParameterProvider::class) fakePokedex: Pokedex,
        navController: NavHostController = rememberNavController()
){
        PokedexCard(data = fakePokedex, navController)
}