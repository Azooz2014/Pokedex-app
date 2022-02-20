package io.blacketron.jetpackcomposepokedex.data.remote.response

data class Move(
    val move: MoveX,
    val versionGroupDetails: List<VersionGroupDetail>
)