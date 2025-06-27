package net.alphadev.jsonfeed.exporter

import net.alphadev.jsonfeed.format.JsonFeed
import kotlinx.serialization.json.Json

fun formatJsonFeed(jsonFeed: JsonFeed): String? = try {
    Json.encodeToString(JsonFeed.serializer(), jsonFeed) + "\n"
} catch (_: Throwable) {
    null
}
