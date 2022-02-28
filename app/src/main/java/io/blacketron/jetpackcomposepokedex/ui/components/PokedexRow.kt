package io.blacketron.jetpackcomposepokedex.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import io.blacketron.jetpackcomposepokedex.data.model.Pokedex

@Composable
fun PokedexRow(
    rowIndex: Int,
    entries: List<Pokedex>,
    navController: NavController
) {
    Column {
        Row {
            PokedexCard(
                data = entries[rowIndex * 2],
                navController = navController,
                modifier = Modifier.weight(1f)
            )
            Spacer(modifier = Modifier.width(16.dp))
            if (entries.size >= rowIndex * 2 + 2) {
                PokedexCard(
                    data = entries[rowIndex * 2 + 1],
                    navController = navController,
                    modifier = Modifier.weight(1f)
                )
            } else {
                Spacer(modifier = Modifier.weight(1f))
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
    }
}

