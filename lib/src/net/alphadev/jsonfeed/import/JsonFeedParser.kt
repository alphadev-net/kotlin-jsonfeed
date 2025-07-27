package net.alphadev.jsonfeed.import

import kotlinx.serialization.json.Json
import net.alphadev.jsonfeed.format.JsonFeed

fun parseJsonFeed(input: String): JsonFeed = try {
    Json.decodeFromString<JsonFeed>(input)
} catch (ex: Throwable) {
    throw FeedParsingException(cause = ex)
}
