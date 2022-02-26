package io.blacketron.jetpackcomposepokedex.screen.pokemon_detail_screen

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.blacketron.jetpackcomposepokedex.data.remote.response.Pokemon
import io.blacketron.jetpackcomposepokedex.data.repository.PokeRepositroy
import io.blacketron.jetpackcomposepokedex.util.Resource
import javax.inject.Inject

@HiltViewModel
class PokemonDetailViewModel @Inject constructor(
    private val repositroy: PokeRepositroy
) : ViewModel() {

    suspend fun getPokemonInfo(pokemonName: String): Resource<Pokemon>{
        return repositroy.getPokemonInfo(pokemonName)
    }
}