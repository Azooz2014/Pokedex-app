package io.blacketron.jetpackcomposepokedex.util

object Constants {

    const val BASE_URL = "https://pokeapi.co/api/v2/"

    const val PAGE_LIMIT = 20

    const val DATABASE_NAME = "pokemon.db"

    const val POKEMON_TABLE_NAME = "pokemon_table"

    const val POKEMON_REMOTE_KEYS_TABLE_NAME = "pokemon_remote_keys_table"

    //1440 minutes = 24h (60min * 24h = 1440min)
    const val CACHE_VALIDITY_IN_MINUETS = 1440
}