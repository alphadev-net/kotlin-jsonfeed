package net.alphadev.jsonfeed.import

import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import net.alphadev.jsonfeed.format.JsonFeed

private val json = Json {}

fun parseJsonFeed(input: String): JsonFeed? = try {
    json.decodeFromString<JsonFeed>(input)
} catch (_: Throwable) {
    null
}
