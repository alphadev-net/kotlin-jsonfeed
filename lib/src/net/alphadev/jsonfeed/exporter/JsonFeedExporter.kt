package net.alphadev.jsonfeed.exporter

import kotlinx.serialization.json.Json
import net.alphadev.jsonfeed.format.JsonFeed
import net.alphadev.jsonfeed.format.JsonFeedInternal

fun formatJsonFeed(jsonFeed: JsonFeed): String? = try {
    Json.encodeToString(JsonFeedInternal.serializer(), jsonFeed.toJsonFeedInternal()) + "\n"
} catch (_: Throwable) {
    null
}

internal fun JsonFeed.toJsonFeedInternal() = JsonFeedInternal(
    version = version,
    title = title,
    homePageUrl = homePageUrl,
    feedUrl = feedUrl,
    description = description,
    userComment = userComment,
    nextUrl = nextUrl,
    icon = icon,
    favicon = favicon,
    v1author = null,
    authors = authors,
    language = language,
    expired = expired,
    items = items
)
