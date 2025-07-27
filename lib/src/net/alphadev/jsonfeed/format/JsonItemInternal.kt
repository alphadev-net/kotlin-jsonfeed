package net.alphadev.jsonfeed.format

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class JsonItemInternal(
    val id: String? = null,

    val url: String? = null,

    @SerialName("external_url")
    val externalUrl: String? = null,

    val title: String? = null,

    @SerialName("content_text")
    val contentText: String? = null,

    @SerialName("content_html")
    val contentHtml: String? = null,

    val summary: String? = null,

    val image: String? = null,

    @SerialName("banner_image")
    val bannerImage: String? = null,

    @SerialName("date_published")
    val datePublished: String? = null,

    @SerialName("date_modified")
    val dateModified: String? = null,

    val authors: List<JsonAuthor>? = null,

    val tags: List<String>? = null,

    val language: String? = null,

    val attachments: List<JsonAttachment>? = null
)
