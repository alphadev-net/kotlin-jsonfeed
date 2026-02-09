package net.alphadev.jsonfeed.format

import kotlin.time.ExperimentalTime

internal const val JsonFeed1 = "https://jsonfeed.org/version/1"
internal const val JsonFeed11 = "https://jsonfeed.org/version/1.1"

@ExperimentalTime
public data class JsonFeed(
    val version: String,

    val title: String,

    val homePageUrl: String? = null,

    val feedUrl: String? = null,

    val description: String? = null,

    val userComment: String? = null,

    val nextUrl: String? = null,

    val icon: String? = null,

    val favicon: String? = null,

    val authors: List<JsonAuthor> = emptyList(),

    val language: String? = null,

    val expired: Boolean? = false,

    val items: List<JsonItem>
)
