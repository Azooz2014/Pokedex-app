package io.blacketron.jetpackcomposepokedex.data.remote.response

data class Ability(
    val ability: AbilityX,
    val isHidden: Boolean,
    val slot: Int
)