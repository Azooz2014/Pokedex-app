package io.blacketron.jetpackcomposepokedex.presentation.screen.pokemon_detail_screen.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.blacketron.jetpackcomposepokedex.data.local.model.Pokemon
import io.blacketron.jetpackcomposepokedex.data.remote.response.*
import io.blacketron.jetpackcomposepokedex.util.parseStatToAbbr
import io.blacketron.jetpackcomposepokedex.util.parseStatToColor

@Composable
fun PokemonBaseStats(
    pokemonInfo: Pokemon,
    animDelayPerItem: Int = 100
) {
    val maxBaseStat = remember {
        pokemonInfo.stats.maxOf { it.baseStat }
    }
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = "Base stats:",
            fontSize = 20.sp,
            color = MaterialTheme.colors.onSurface
        )
        Spacer(modifier = Modifier.height(4.dp))

        for (i in pokemonInfo.stats.indices) {
            val stat = pokemonInfo.stats[i]
            PokemonStat(
                statName = parseStatToAbbr(stat),
                statValue = stat.baseStat,
                statMaxValue = maxBaseStat,
                statColor = parseStatToColor(stat),
                animDelay = i * animDelayPerItem
            )
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}

//////////////////////////////////////////
// PREVIEW
/////////////////////////////////////////

@Preview
@Composable
fun PokemonBaseStatsPreview() {

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

    PokemonBaseStats(pokemonInfo = Pokemon(
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
    ))
}