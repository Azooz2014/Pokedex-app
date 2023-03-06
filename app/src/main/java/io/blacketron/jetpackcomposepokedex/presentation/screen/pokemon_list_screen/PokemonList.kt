package io.blacketron.jetpackcomposepokedex.presentation.screen.pokemon_list_screen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.navigation.NavController
import androidx.paging.ExperimentalPagingApi
import androidx.paging.compose.collectAsLazyPagingItems

@OptIn(ExperimentalFoundationApi::class)
@ExperimentalPagingApi
@Composable
fun PokemonList(
    navController: NavController,
    viewModel: PokemonListViewModel
){
//    val pokemonList by remember{viewModel.pokemonList}
    val pokemons = viewModel.getPokemons.collectAsLazyPagingItems()
    val endReached by remember{viewModel.endReached}
    val loadError by remember{viewModel.loadError}
    val isLoading by remember{viewModel.isLoading}
    val isSearching by remember{viewModel.isSearching}

    //TODO: To be replaced by LazyGrid https://developer.android.com/jetpack/compose/lists 
    
    LazyVerticalGrid(cells = GridCells.Fixed(2)){
       items(
           count = pokemons.itemCount
       ){
           index ->
           /*pokemons[index]?.pokemons?.forEach {
               Text(text = it.name)
           }*/
           pokemons[index]?.pokemons?.forEach {
               pokemon ->
               PokedexCard(data = pokemon, navController = navController)
           }

       }
    }
    
   /* LazyColumn(contentPadding = PaddingValues(16.dp)){

        *//*Calculating the number of items, each row containing 2 pokemons,
        * if the number of paginated pokemons are odd then add 1 to display the remaining
        * pokemon*//*
        *//*val itemCount = if(pokemonList.size % 2 == 0){
            pokemonList.size / 2
        } else {
            pokemonList.size / 2 + 1
        }
        items(itemCount){ index ->

            *//**//*Checking if user reached to the bottom of the list.
            * if true the load new pokemons*//**//*
            if(index >= itemCount - 1 && !endReached && !isLoading && !isSearching){
                viewModel.loadPokemonPaginated()
            }

            *//**//**Displaying 2 [PokedexCard]s on each row*//**//*
            PokedexRow(rowIndex = index, entries = pokemonList, navController = navController)
        }*//*

        items(
            items = pokemons,
            key = {
                pokemon -> pokemon.pokedex.id
            }
        ){
            it?.pokemons?.forEach { 
                Card() {
                    Text(text = it.name)
                }
            }
        }
    }*/

    /*Box(
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
    }*/

}