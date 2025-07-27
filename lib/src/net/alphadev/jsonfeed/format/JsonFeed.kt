package net.alphadev.jsonfeed.format

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

const val JsonFeed1 = "https://jsonfeed.org/version/1"
const val JsonFeed11 = "https://jsonfeed.org/version/1.1"

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

    val author: String? = null,

    val authors: List<JsonAuthor> = emptyList(),

    val language: String? = null,

    val expired: Boolean? = false,

    val items: List<JsonItem>
)
