package net.alphadev.jsonfeed.format

import net.alphadev.jsonfeed.import.toJsonFeed
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertIs
import kotlin.test.assertTrue

class JsonFeedTest {

    @Test
    fun testV1WithAuthorIsAutomaticallyConvertedToV11Authors() {
        val input = JsonFeedInternal(
            version = JsonFeed1,
            title = "some title",
            items = emptyList(),
            v1author = "test"
        )

        val feed = input.toJsonFeed()
        assertIs<JsonFeed>(feed)
        assertEquals("test", feed.authors.first().name)
    }

    @Test
    fun testV11WithAuthorIsAutomaticallyConvertedToV11Authors() {
        val input = JsonFeedInternal(
            version = JsonFeed11,
            title = "some title",
            items = emptyList(),
            v1author = "old author"
        )

        val feed = input.toJsonFeed()
        assertIs<JsonFeed>(feed)
        assertEquals("old author", feed.authors.first().name)
    }

    @Test
    fun testV11BothWithAuthorAndAuthorsIsAutomaticallyMerged() {
        val input = JsonFeedInternal(
            version = JsonFeed11,
            title = "some title",
            items = emptyList(),
            v1author = "old author",
            authors = listOf(JsonAuthor(name = "test"))
        )

        val feed = input.toJsonFeed()
        assertIs<JsonFeed>(feed)
        assertEquals("old author", feed.authors[0].name)
        assertEquals("test", feed.authors[1].name)
    }

    @Test
    fun testV1WithoutAuthorReturnsEmptyV11AuthorsList() {
        val input = JsonFeedInternal(
            version = JsonFeed11,
            title = "some title",
            items = emptyList(),
        )

        val feed = input.toJsonFeed()
        assertIs<JsonFeed>(feed)
        assertTrue(feed.authors.isEmpty())
    }
}
