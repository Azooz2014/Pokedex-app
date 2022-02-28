package io.blacketron.jetpackcomposepokedex.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import io.blacketron.jetpackcomposepokedex.screen.pokemon_list_screen.PokemonListViewModel

@Composable
fun PokemonList(
    navController: NavController,
    viewModel: PokemonListViewModel = hiltViewModel()
){
    val pokemonList by remember{viewModel.pokemonList}
    val endReached by remember{viewModel.endReached}
    val loadError by remember{viewModel.loadError}
    val isLoading by remember{viewModel.isLoading}
    val isSearching by remember{viewModel.isSearching}

    LazyColumn(contentPadding = PaddingValues(16.dp)){

        /*Calculating the number of items, each row containing 2 pokemons,
        * if the number of paginated pokemons are odd then add 1 to display the remaining
        * pokemon*/
        val itemCount = if(pokemonList.size % 2 == 0){
            pokemonList.size / 2
        } else {
            pokemonList.size / 2 + 1
        }
        items(itemCount){ index ->

            /*Checking if user reached to the bottom of the list.
            * if true the load new pokemons*/
            if(index >= itemCount - 1 && !endReached && !isLoading && !isSearching){
                viewModel.loadPokemonPaginated()
            }

            /**Displaying 2 [PokedexCard]s on each row*/
            PokedexRow(rowIndex = index, entries = pokemonList, navController = navController)
        }
    }

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ){
        if(isLoading){
            CircularProgressIndicator(color = MaterialTheme.colors.primary)
        }
        if(loadError.isNotEmpty()){
            RetrySection(error = loadError) {
                viewModel.loadPokemonPaginated()
            }
        }
    }

}