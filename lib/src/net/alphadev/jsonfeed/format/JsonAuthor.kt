package net.alphadev.jsonfeed.format

import kotlinx.serialization.Serializable

@Serializable
data class JsonAuthor(
    val name: String? = null,
    val url: String? = null,
    val avatar: String? = null
)
