package io.blacketron.jetpackcomposepokedex.presentation.screen.pokemon_detail_screen

import androidx.lifecycle.ViewModel
import androidx.paging.ExperimentalPagingApi
import dagger.hilt.android.lifecycle.HiltViewModel
import io.blacketron.jetpackcomposepokedex.data.remote.response.Pokemon
import io.blacketron.jetpackcomposepokedex.data.repository.PokeRepositroyImp
import io.blacketron.jetpackcomposepokedex.util.Resource
import javax.inject.Inject

@HiltViewModel
@ExperimentalPagingApi
class PokemonDetailViewModel @Inject constructor(
    /*private val repositroy: PokeRepositroyImp*/
) : ViewModel() {

    /*suspend fun getPokemonInfo(pokemonName: String): Resource<Pokemon>{
        return repositroy.getPokemonInfo(pokemonName)
    }*/
}