package net.alphadev.jsonfeed.import

import kotlinx.io.readString
import net.alphadev.jsonfeed.format.JsonFeed
import net.alphadev.jsonfeed.readResource
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertIs
import kotlin.time.ExperimentalTime
import kotlin.time.Instant

@OptIn(ExperimentalTime::class)
class JsonFeedParsingTest {

    @Test
    fun testJsonFeedOrg() {
        readResource("jsonfeed-org.json") { input ->
            val feed = parseJsonFeed(input.readString())
            assertEquals(2, feed.items.size)

            val firstItem = feed.items.first()
            assertEquals(Instant.fromEpochSeconds(1596818676), firstItem.datePublished)
        }
    }

    @Test
    fun testJanAlphadevNet() {
        readResource("jan-alphadev-net.json") { input ->
            val feed = parseJsonFeed(input.readString())
            assertEquals("Jans Stuff", feed.title)
            assertEquals(10, feed.items.size)
        }
    }

    @Test
    fun testFeedWithOnlyTheRequiredFieldsParses() {
        val input = """
        {
            "version": "https://jsonfeed.org/version/1",
            "title": "JSON Feed",
            "items": []
        }
        """.trimIndent()

        val feed = parseJsonFeed(input)
        assertIs<JsonFeed>(feed)
    }

    @Test
    fun testFeedWithoutVersionThrowsError() {
        val input = """
        {
            "title": "JSON Feed",
            "items": []
        }
        """.trimIndent()

        assertFailsWith<FeedParsingException> {
            parseJsonFeed(input)
        }
    }

    @Test
    fun testFeedWithoutTitleThrowsError() {
        val input = """
        {
            "version": "https://jsonfeed.org/version/1",
            "items": []
        }
        """.trimIndent()

        assertFailsWith<FeedParsingException> {
            parseJsonFeed(input)
        }
    }

    @Test
    fun testFeedWithoutItemsThrowsError() {
        val input = """
        {
            "version": "https://jsonfeed.org/version/1",
            "title": "JSON Feed"
        }
        """.trimIndent()

        assertFailsWith<FeedParsingException> {
            parseJsonFeed(input)
        }
    }
}
