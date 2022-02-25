package io.blacketron.jetpackcomposepokedex.screen.pokemon_list_screen

import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.palette.graphics.Palette
import dagger.hilt.android.lifecycle.HiltViewModel
import io.blacketron.jetpackcomposepokedex.data.repository.PokeRepositroy
import javax.inject.Inject

@HiltViewModel
class PokemonListViewModel @Inject constructor(
    private val repository: PokeRepositroy
) : ViewModel() {

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
}