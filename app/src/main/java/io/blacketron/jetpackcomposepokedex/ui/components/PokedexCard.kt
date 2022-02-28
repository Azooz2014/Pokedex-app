package io.blacketron.jetpackcomposepokedex.ui.components

import android.graphics.drawable.Drawable
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.annotation.ExperimentalCoilApi
import coil.compose.ImagePainter
import coil.compose.rememberImagePainter
import coil.request.ImageRequest
import coil.transform.GrayscaleTransformation
import io.blacketron.jetpackcomposepokedex.R
import io.blacketron.jetpackcomposepokedex.data.model.Pokedex
import io.blacketron.jetpackcomposepokedex.screen.pokemon_list_screen.PokemonListViewModel
import io.blacketron.jetpackcomposepokedex.ui.theme.RobotoCondensed
import java.util.*


@ExperimentalCoilApi
@Composable
fun PokedexCard(
    data: Pokedex,
    navController: NavController,
    modifier: Modifier = Modifier,
    viewModel: PokemonListViewModel = hiltViewModel()
){
    val defaultDominantColor = MaterialTheme.colors.surface
    var dominantColor by remember {
        mutableStateOf(defaultDominantColor)
    }

    Box(
        contentAlignment = Center,
        modifier = modifier
            .shadow(5.dp, RoundedCornerShape(10.dp))
            .clip(RoundedCornerShape(10.dp))
            .aspectRatio(1f)
            .background(
                Brush.verticalGradient(
                    listOf(
                        dominantColor,
                        defaultDominantColor
                    )
                )
            )
            .clickable {
                navController.navigate(
                    "pokemon_details_screen/${dominantColor.toArgb()}/${data.pokemonName}"
                )
            }
    ) {
        Column {

            val painter = rememberImagePainter(
                data = data.imageUrl,
                builder = {
                    placeholder(R.drawable.ic_poke_ball_grayscale)
                    crossfade(true)
                }
            )

            val painterState = painter.state
            when(painterState){
                /*is ImagePainter.State.Loading ->
                    CircularProgressIndicator(
                        modifier = Modifier
                            .align(CenterHorizontally)
                            .padding(top = 38.dp),
                        color = MaterialTheme.colors.primary
                    )*/

                is ImagePainter.State.Success ->
                    LaunchedEffect(Unit){
                        val drawable = painterState.result.drawable
                        viewModel.calcDominantColor(drawable) { color ->
                            dominantColor = color
                        }
                    }
                else -> {}
            }

            Image(
                painter = painter,
                contentDescription = data.pokemonName,
                modifier = Modifier
                    .size(120.dp)
                    .align(CenterHorizontally)
            )

            /*(painter.state).let {
                when(it){
                    is ImagePainter.State.Loading ->
                        CircularProgressIndicator(
                            modifier = Modifier
                                .align(CenterHorizontally),
                            color = MaterialTheme.colors.primary
                        )
                    is ImagePainter.State.Success ->
                        LaunchedEffect(Unit){
                            val drawable = it.result.drawable
                            viewModel.calcDominantColor(drawable) { color ->
                                dominantColor = color
                            }
                        }
                }
            }*/
            /*CoilImage(
                request = ImageRequest.Builder(LocalContext.current)
                    .data(data.imageUrl)
                    .target { drawable ->
                        viewModel.calcDominantColor(drawable) { color ->
                            dominantColor = color
                        }
                    }
                    .build(),
                contentDescription = data.pokemonName,
                fadeIn = true,
                modifier = Modifier
                    .size(120.dp)
                    .align(Alignment.CenterHorizontally)
            ) {
                CircularProgressIndicator(
                    modifier = Modifier
                        .scale(.5f),
                    color = MaterialTheme.colors.primary
                )
            }*/
            Text(
                text = data.pokemonName.capitalize(Locale.ROOT),
                fontFamily = RobotoCondensed,
                fontSize = 20.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}