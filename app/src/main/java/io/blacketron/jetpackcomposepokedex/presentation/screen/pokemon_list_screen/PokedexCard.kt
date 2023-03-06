package io.blacketron.jetpackcomposepokedex.presentation.screen.pokemon_list_screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.paging.ExperimentalPagingApi
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import io.blacketron.jetpackcomposepokedex.R
import io.blacketron.jetpackcomposepokedex.data.local.model.Pokemon
import io.blacketron.jetpackcomposepokedex.data.remote.response.*
import io.blacketron.jetpackcomposepokedex.presentation.ui.theme.RobotoCondensed
import java.util.*


@ExperimentalPagingApi
@ExperimentalCoilApi
@Composable
fun PokedexCard(
    data: Pokemon,
    navController: NavController,
    dominantColor: Color = MaterialTheme.colors.surface,
){
    val defaultDominantColor = MaterialTheme.colors.surface
    /*var dominantColor by remember {
        mutableStateOf(defaultDominantColor)
    }*/

    Box(
        contentAlignment = Center,
        modifier = Modifier
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
                    "pokemon_details_screen/${dominantColor.toArgb()}/${data.name}"
                )
            }
    ) {
        Column {

            val painter = rememberImagePainter(
                data = data.sprites.other.officialArtwork.frontDefault,
                builder = {
                    placeholder(R.drawable.ic_poke_ball_grayscale)
                    crossfade(true)
                }
            )

            /*val painterState = painter.state
            when(painterState){
                *//*is ImagePainter.State.Loading ->
                    CircularProgressIndicator(
                        modifier = Modifier
                            .align(CenterHorizontally)
                            .padding(top = 38.dp),
                        color = MaterialTheme.colors.primary
                    )*//*

                is ImagePainter.State.Success ->
                    LaunchedEffect(Unit){
                        val drawable = painterState.result.drawable
                        viewModel.calcDominantColor(drawable) { color ->
                            dominantColor = color
                        }
                    }
                else -> {}
            }*/

            Image(
                painter = painter,
                contentDescription = data.name,
                modifier = Modifier
                    .size(120.dp)
                    .align(CenterHorizontally)
            )

            /*(painter.state).let { state ->
                when(state){
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
                text = data.name.capitalize(Locale.ROOT),
                fontFamily = RobotoCondensed,
                fontSize = 20.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

////////////////////////////////////////////
// PREVIEW
///////////////////////////////////////////

@OptIn(ExperimentalCoilApi::class)
@ExperimentalPagingApi
@Preview
@Composable
fun PokemonCardPreview(
    navController: NavHostController = rememberNavController()
){
    /*val data = Pokedex(pokemonName = "ditto",
        imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/132.png",
        pokemonNumber = 132)*/

    /*val data = PokeRemoteKeys(
        id = 132,
        prevKey = 0,
        nextKey = 0,
        name = "ditto",
        pokemonUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/132.png",
        lastUpdate = 12345
    )*/

    val version = Versions(
        generationI = GenerationI(
            redBlue = RedBlue(
                backDefault = "",
                backGray = "",
                backTransparent = "",
                frontDefault = "",
                frontGray = "",
                frontTransparent = ""
            ),
            yellow = Yellow(
                backDefault = "",
                backGray = "",
                backTransparent = "",
                frontDefault = "",
                frontGray = "",
                frontTransparent = ""
            )
        ),
        generationIi = GenerationIi(
            crystal = Crystal(
                backDefault = "",
                backShiny = "",
                backShinyTransparent = "",
                backTransparent = "",
                frontDefault = "",
                frontShiny = "",
                frontTransparent = "",
                frontShinyTransparent = ""
            ),
            gold = Gold(
                backDefault = "",
                backShiny = "",
                frontDefault = "",
                frontShiny = "",
                frontTransparent = ""
            ),
            silver = Silver(
                backDefault = "",
                backShiny = "",
                frontDefault = "",
                frontShiny = "",
                frontTransparent = "",
            )
        ),
        generationIii = GenerationIii(
            emerald = Emerald(
                frontDefault = "",
                frontShiny = ""
            ),
            fireredLeafgreen = FireredLeafgreen(
                backDefault = "",
                backShiny = "",
                frontDefault = "",
                frontShiny = ""
            ),
            rubySapphire = RubySapphire(
                backDefault = "",
                backShiny = "",
                frontDefault = "",
                frontShiny = ""
            )
        ),
        generationIv = GenerationIv(
            diamondPearl = DiamondPearl(
                backDefault = "",
                backFemale = "",
                backShiny = "",
                backShinyFemale = "",
                frontDefault = "",
                frontFemale = "",
                frontShiny = "",
                frontShinyFemale = ""
            ),
            heartgoldSoulsilver = HeartgoldSoulsilver(
                backDefault = "",
                backFemale = "",
                backShiny = "",
                backShinyFemale = "",
                frontDefault = "",
                frontFemale = "",
                frontShiny = "",
                frontShinyFemale = ""
            ),
            platinum = Platinum(
                backDefault = "",
                backFemale = "",
                backShiny = "",
                backShinyFemale = "",
                frontDefault = "",
                frontFemale = "",
                frontShiny = "",
                frontShinyFemale = ""
            )
        ),
        generationV = GenerationV(
            blackWhite = BlackWhite(
                animated = Animated(
                    backDefault = "",
                    backFemale = "",
                    backShiny = "",
                    backShinyFemale = "",
                    frontDefault = "",
                    frontFemale = "",
                    frontShiny = "",
                    frontShinyFemale = ""
                ),
                backDefault = "",
                backFemale = "",
                backShiny = "",
                backShinyFemale = "",
                frontDefault = "",
                frontFemale = "",
                frontShiny = "",
                frontShinyFemale = ""
            )
        ),
        generationVi = GenerationVi(
            omegarubyAlphasapphire = OmegarubyAlphasapphire(
                frontDefault = "",
                frontFemale = "",
                frontShiny = "",
                frontShinyFemale = ""
            ),
            xY = XY(
                frontDefault = "",
                frontFemale = "",
                frontShiny = "",
                frontShinyFemale = ""
            )
        ),
        generationVii = GenerationVii(
            icons = Icons(
                frontDefault = "",
                frontFemale = ""
            ),
            ultraSunUltraMoon = UltraSunUltraMoon(
                frontDefault = "",
                frontFemale = "",
                frontShiny = "",
                frontShinyFemale = ""
            )
        ),
        generationViii = GenerationViii(
            icons = IconsX(
                frontDefault = "",
                frontFemale = ""
            )
        )
    )

    val sprites = Sprites(
        backDefault = "",
        backFemale = "",
        backShiny = "",
        backShinyFemale = "",
        frontDefault = "",
        frontFemale = "",
        frontShiny = "",
        frontShinyFemale = "",
        other = Other(
            DreamWorld(frontDefault = "", frontFemale = ""),
            Home(frontDefault = "", frontFemale = "", frontShiny = "", frontShinyFemale = ""),
            officialArtwork = OfficialArtwork(frontDefault = "")
        ),
        versions = version
    )

    val data = Pokemon(
        id = 1,
        name = "Bolbasor",
        abilities = emptyList(),
        baseExperience = 10,
        forms = emptyList(),
        gameIndices = emptyList(),
        height = 10,
        heldItems = emptyList(),
        isDefault = false,
        locationAreaEncounters = "",
        moves = emptyList(),
        order = 1,
        pastTypes = emptyList(),
        species = Species(
            name = "Bolbasor",
            url = ""
        ),
        sprites = sprites,
        stats = emptyList(),
        types = emptyList(),
        weight = 10
    )

    PokedexCard(data = data, navController)
}