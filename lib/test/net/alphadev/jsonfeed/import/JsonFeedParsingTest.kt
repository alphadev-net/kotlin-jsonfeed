package net.alphadev.jsonfeed.import

import de.sipgate.dachlatten.io.readResource
import kotlinx.io.readString
import net.alphadev.jsonfeed.format.JsonFeed
import net.alphadev.jsonfeed.format.useText
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

        assertIs<JsonFeed>(parseJsonFeed(input))
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

    @Test
    fun testFeedBothAuthorAndAuthorsSet() {
        val input = """
        {
            "version": "https://jsonfeed.org/version/1",
            "title": "JSON Feed",
            "author": "test",
            "authors": [
                {
                    "name": "test2"
                }
            ],
            "items": []
        }
        """.trimIndent()

        assertIs<JsonFeed>(parseJsonFeed(input))
    }

    @Test
    fun testV1WithV11LanguageFieldSetFails() {
        val input = """
        {
            "version": "https://jsonfeed.org/version/1",
            "title": "JSON Feed",
            "language": "EN",
            "items": []
        }
        """.trimIndent()

        assertFailsWith<FeedParsingException> {
            parseJsonFeed(input)
        }
    }



    @Test
    fun testItemIsSkippedWhenIdIsMissing() {
        val input = """
        {
            "version": "https://jsonfeed.org/version/1",
            "title": "JSON Feed",
            "items": [
                {
                  "id": "http://jsonfeed.micro.blog/2020/08/07/json-feed-version.html",
                  "content_text": "test"
                },
                {
                  "content_text": "test2"
                }
            ]
        }
        """.trimIndent()

        val feed = parseJsonFeed(input)
        assertEquals(1, feed.items.size)
        assertEquals("test", feed.items.first().content.useText)
    }
}
