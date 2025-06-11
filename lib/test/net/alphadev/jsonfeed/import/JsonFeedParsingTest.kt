package net.alphadev.jsonfeed.import

import kotlinx.io.readString
import kotlinx.serialization.json.Json
import kotlin.test.Test
import kotlin.test.assertEquals
import net.alphadev.jsonfeed.readResource
import net.alphadev.jsonfeed.format.JsonFeed

class JsonFeedParsingTest {

    @Test
    fun testJsonFeedOrg() {
        readResource("jsonfeed-org.json") { input ->
            val feed = Json.decodeFromString<JsonFeed>(input.readString())
            assertEquals(2, feed.items.size)
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
