package net.alphadev.jsonfeed.format

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class JsonAttachment(
    val url: String,
    @SerialName("mime_type")
    val mimeType: String,
    val title: String? = null,
    @SerialName("size_in_bytpes")
    val sizeInBytes: Long? = null,
    @SerialName("duration_in_seconds")
    val durationInSeconds: Long? = null
)
