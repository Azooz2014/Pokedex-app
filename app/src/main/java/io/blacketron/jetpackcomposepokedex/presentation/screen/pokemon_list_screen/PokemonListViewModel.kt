package io.blacketron.jetpackcomposepokedex.presentation.screen.pokemon_list_screen

import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.paging.ExperimentalPagingApi
import androidx.palette.graphics.Palette
import dagger.hilt.android.lifecycle.HiltViewModel
import io.blacketron.jetpackcomposepokedex.data.repository.PokeRepositroyImp
import io.blacketron.jetpackcomposepokedex.presentation.model.Pokedex
import javax.inject.Inject

@ExperimentalPagingApi
@HiltViewModel
class PokemonListViewModel @Inject constructor(
    private val repository: PokeRepositroyImp
) : ViewModel() {

    private var curPage = 0
    private var cachedPokemonList = listOf<Pokedex>()
    private var isSearchStarting = true

//    var pokemonList = mutableStateOf<List<Pokedex>>(listOf())


    var loadError = mutableStateOf("")

    var isLoading = mutableStateOf(false)

    var endReached = mutableStateOf(false)

    var isSearching = mutableStateOf(false)

    val getPokemons = repository.getPokemons()

/*    init {
        loadPokemonPaginated()
    }*/

    /*fun loadPokemonPaginated(){
        isLoading.value = true
        val pokemonList: Flow<PagingData<PokedexWithPokemons>> = Pager(

        )
        viewModelScope.launch {
//            val result = repository.getPokemonList(PAGE_LIMIT, curPage * PAGE_LIMIT)
              val result = repository.getPokemons().collectLatest { data ->

                  val pokedexList = data.map {
                    Pokedex(
                        pokemonName = it.pokedex.name,
                        imageUrl = it.pokedex.pokemonUrl,
                        pokemonNumber = it.pokedex.id
                    )
                  }

                  loadError.value = ""
                  isLoading.value = false
                  pokemonList.value += pokedexList
              }

            when(result){
                is Resource.Success ->{
                    endReached.value = curPage * PAGE_LIMIT >= result.data!!.count
                    val pokedexList = result.data.results.mapIndexed { index, result ->

                        *//*getting the pokemon URL from the api result and extracting
                        * the pokemon number so it can be used to load the pokemon image in the list
                        * without the need to query for individual pokemons.
                        *
                        * i.ie url is https://pokeapi.co/api/v2/pokemon/1/ -> 1*//*
                        val pokeNumber = if(result.url.endsWith("/")){
                            result.url.dropLast(1).takeLastWhile { it.isDigit() }
                        }else {
                            result.url.takeLastWhile { it.isDigit() }
                        }
                        val pokeImageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/${pokeNumber}.png"
                        Pokedex(result.name, pokeImageUrl, pokeNumber.toInt())
                    }

                    curPage++

                    loadError.value = ""
                    isLoading.value = false
                    pokemonList.value += pokedexList
                    Timber.i("pokemon list size: ${pokemonList.value.size}")
                }
                is Resource.Error ->{
                    loadError.value = result.message!!
                    isLoading.value = false
                }
            }
        }
    }*/

    fun calcDominantColor(drawable: Drawable, onFinish: (Color) -> Unit) {

        val bitmap = (drawable as BitmapDrawable).bitmap.copy(
            Bitmap.Config.ARGB_8888, true
        )

        Palette.from(bitmap).generate { palette ->
            palette?.dominantSwatch?.rgb?.let { colorValue ->
                onFinish(Color(colorValue))
            }
        }
    }

    fun searchPokemonList(query: String){
        /*val listToSearch = if(isSearchStarting){
            pokemonList.value
        }else{
            cachedPokemonList
        }

        viewModelScope.launch(Dispatchers.Default) {
            if(query.isEmpty()){
                pokemonList.value = cachedPokemonList
                isSearching.value = false
                isSearchStarting = true
                return@launch
            }
            val results = listToSearch.filter {
                it.pokemonName.contains(query.trim(), ignoreCase = true) ||
                        it.pokemonNumber.toString() == query.trim()
            }

            if(isSearchStarting){
                cachedPokemonList = pokemonList.value
                isSearchStarting = false
            }
            pokemonList.value = results
            isSearching.value = true
        }*/
    }
}