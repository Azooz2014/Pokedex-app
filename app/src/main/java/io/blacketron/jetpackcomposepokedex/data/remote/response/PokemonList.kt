package io.blacketron.jetpackcomposepokedex.data.remote.response

data class PokemonList(
    val count: Int,
    val next: String? = null,
    val previous: String? = null,
    val results: List<Result>
)