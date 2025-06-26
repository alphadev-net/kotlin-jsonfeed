package net.alphadev.jsonfeed.import

import kotlinx.io.readString
import kotlinx.serialization.json.Json
import kotlin.test.Test
import kotlin.test.assertEquals
import net.alphadev.jsonfeed.readResource
import net.alphadev.jsonfeed.format.JsonFeed
import kotlin.time.ExperimentalTime
import kotlin.time.Instant

@OptIn(ExperimentalTime::class)
class JsonFeedParsingTest {

    @Test
    fun testJsonFeedOrg() {
        readResource("jsonfeed-org.json") { input ->
            val feed = Json.decodeFromString<JsonFeed>(input.readString())
            assertEquals(2, feed.items.size)

            val firstItem = feed.items.first()
            assertEquals(Instant.fromEpochSeconds(1596818676), firstItem.datePublished)
        }
    }

    @Test
    fun testJanAlphadevNet() {
        readResource("jan-alphadev-net.json") { input ->
            val feed = Json.decodeFromString<JsonFeed>(input.readString())
            assertEquals("Jans Stuff", feed.title)
            assertEquals(10, feed.items.size)
        }
    }
}
