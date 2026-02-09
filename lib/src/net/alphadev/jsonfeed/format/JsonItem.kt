package net.alphadev.jsonfeed.format

import kotlin.time.ExperimentalTime
import kotlin.time.Instant

@ExperimentalTime
data class JsonItem(
    val id: String,
    val url: String? = null,
    val externalUrl: String? = null,
    val title: String? = null,
    val content: ItemContent,
    val summary: String? = null,
    val image: String? = null,
    val bannerImage: String? = null,
    val datePublished: Instant? = null,
    val dateModified: Instant? = null,
    val authors: List<JsonAuthor>? = null,
    val tags: List<String>? = null,
    val language: String? = null,
    val attachments: List<JsonAttachment>? = null
)
