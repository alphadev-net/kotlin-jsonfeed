package net.alphadev.jsonfeed.import

import kotlinx.serialization.json.Json
import net.alphadev.jsonfeed.format.JsonFeed

fun parseJsonFeed(input: String): JsonFeed? = try {
    Json.decodeFromString<JsonFeed>(input)
} catch (_: Throwable) {
    null
}
