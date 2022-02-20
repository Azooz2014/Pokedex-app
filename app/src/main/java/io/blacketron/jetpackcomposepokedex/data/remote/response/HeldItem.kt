package io.blacketron.jetpackcomposepokedex.data.remote.response

data class HeldItem(
    val item: Item,
    val versionDetails: List<VersionDetail>
)