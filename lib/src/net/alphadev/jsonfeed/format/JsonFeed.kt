package net.alphadev.jsonfeed.format

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class JsonFeed(
    val version: String,
    val title: String,
    @SerialName("home_page_url")
    val homePageUrl: String? = null,
    @SerialName("feed_url")
    val feedUrl: String? = null,
    val description: String? = null,
    @SerialName("user_comment")
    val userComment: String? = null,
    @SerialName("next_url")
    val nextUrl: String? = null,
    val icon: String? = null,
    val favicon: String? = null,
    val authors: List<JsonAuthor>? = null,
    val language: String? = null,
    val expired: Boolean? = false,
    val items: List<JsonItem>
)
