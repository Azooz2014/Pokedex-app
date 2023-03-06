package io.blacketron.jetpackcomposepokedex.data.remote.response


import com.google.gson.annotations.SerializedName

data class GameIndex(
    @SerializedName("game_index")
    val gameIndex: Int,
    val version: Version
)