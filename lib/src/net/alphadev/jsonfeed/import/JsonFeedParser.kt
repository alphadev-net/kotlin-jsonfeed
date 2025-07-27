package net.alphadev.jsonfeed.import

import kotlinx.serialization.json.Json
import net.alphadev.jsonfeed.format.JsonFeed
import net.alphadev.jsonfeed.format.JsonFeedInternal
import net.alphadev.jsonfeed.import.toJsonFeed

fun parseJsonFeed(input: String): JsonFeed = try {
    Json.decodeFromString<JsonFeedInternal>(input).toJsonFeed()
} catch (ex: Throwable) {
    throw FeedParsingException(cause = ex)
}
