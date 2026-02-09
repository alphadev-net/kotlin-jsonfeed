package net.alphadev.jsonfeed.exporter

import kotlinx.serialization.json.Json
import net.alphadev.jsonfeed.format.JsonFeed
import net.alphadev.jsonfeed.format.JsonFeedInternal
import kotlin.time.ExperimentalTime

@ExperimentalTime
fun formatJsonFeed(jsonFeed: JsonFeed): String = try {
    Json.encodeToString(JsonFeedInternal.serializer(), jsonFeed.toJsonFeedInternal()) + "\n"
} catch (ex: Throwable) {
    throw FeedExportException(cause = ex)
}
