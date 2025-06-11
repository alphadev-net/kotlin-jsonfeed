package net.alphadev.jsonfeed.exporter

import net.alphadev.jsonfeed.format.JsonFeed
import kotlinx.serialization.json.Json

private const val INDENT_SIZE = 4

private val json = Json {}

fun formatJsonFeed(jsonFeed: JsonFeed): String? = try {
    json.encodeToString(JsonFeed.serializer(), jsonFeed) + "\n"
} catch (_: Throwable) {
    null
}
