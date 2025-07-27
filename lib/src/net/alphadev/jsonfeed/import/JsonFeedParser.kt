package net.alphadev.jsonfeed.import

import kotlinx.serialization.json.Json
import net.alphadev.jsonfeed.format.JsonAuthor
import net.alphadev.jsonfeed.format.JsonFeed
import net.alphadev.jsonfeed.format.JsonFeed11
import net.alphadev.jsonfeed.format.JsonFeedInternal

fun parseJsonFeed(input: String): JsonFeed = try {
    Json.decodeFromString<JsonFeedInternal>(input).toJsonFeed()
} catch (ex: Throwable) {
    throw FeedParsingException(cause = ex)
}

internal fun JsonFeedInternal.toJsonFeed() = JsonFeed(
    version = JsonFeed11,
    title = title,
    homePageUrl = homePageUrl,
    feedUrl = feedUrl,
    description = description,
    userComment = userComment,
    nextUrl = nextUrl,
    icon = icon,
    favicon = favicon,
    authors = buildList {
        if (v1author != null && !authors.any { it.name == v1author }) {
            add(JsonAuthor(name = v1author))
        }

        addAll(authors)
    },
    language = language,
    expired = expired,
    items = items
)
