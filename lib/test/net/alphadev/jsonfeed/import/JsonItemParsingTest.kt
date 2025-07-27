package net.alphadev.jsonfeed.import

import kotlinx.serialization.json.Json
import net.alphadev.jsonfeed.format.*
import kotlin.test.*
import kotlin.time.Instant

class JsonItemParsingTest {
    @Test
    fun testDecodingWithMinimallyRequiredAttributesWorks() {
        val input = """
            {
              "id": "http://jsonfeed.micro.blog/2020/08/07/json-feed-version.html",
              "content_text": "test"
            }
        """.trimIndent()

        val item = Json.decodeFromString<JsonItemInternal>(input).toJsonItem()
        assertEquals("http://jsonfeed.micro.blog/2020/08/07/json-feed-version.html", item.id)
        assertIs<TextContent>(item.content)
    }

    @Test
    fun testHappyPathWorks() {
        val input = """
            {
              "id": "http://jsonfeed.micro.blog/2020/08/07/json-feed-version.html",
              "title": "JSON Feed version 1.1",
              "content_html": "<p>We&rsquo;ve updated the spec to <a href=\"https://jsonfeed.org/version/1.1\">version 1.1</a>. It’s a minor update to JSON Feed, clarifying a few things in the spec and adding a couple new fields such as <code>authors</code> and <code>language</code>.</p>\n\n<p>For version 1.1, we&rsquo;re starting to move to the more specific MIME type <code>application/feed+json</code>. Clients that parse HTML to discover feeds should prefer that MIME type, while still falling back to accepting <code>application/json</code> too.</p>\n\n<p>The <a href=\"https://jsonfeed.org/code/\">code page</a> has also been updated with several new code libraries and apps that support JSON Feed.</p>\n",
              "content_text": "test",
              "date_published": "2020-08-07T11:44:36-05:00",
              "url": "https://www.jsonfeed.org/2020/08/07/json-feed-version.html"
            }
        """.trimIndent()

        val item = Json.decodeFromString<JsonItemInternal>(input).toJsonItem()
        assertEquals("http://jsonfeed.micro.blog/2020/08/07/json-feed-version.html", item.id)
        assertEquals("JSON Feed version 1.1", item.title)
        assertIs<TextAndHtmlContent>(item.content)
        assertEquals(Instant.parse("2020-08-07T11:44:36-05:00"), item.datePublished)
        assertEquals("https://www.jsonfeed.org/2020/08/07/json-feed-version.html", item.url)
    }

    @Test
    fun testDateParsingErrorThrowsParsingException() {
        val input = """
            {
              "id": "http://jsonfeed.micro.blog/2020/08/07/json-feed-version.html",
              "title": "JSON Feed version 1.1",
              "content_html": "<p>We&rsquo;ve updated the spec to <a href=\"https://jsonfeed.org/version/1.1\">version 1.1</a>. It’s a minor update to JSON Feed, clarifying a few things in the spec and adding a couple new fields such as <code>authors</code> and <code>language</code>.</p>\n\n<p>For version 1.1, we&rsquo;re starting to move to the more specific MIME type <code>application/feed+json</code>. Clients that parse HTML to discover feeds should prefer that MIME type, while still falling back to accepting <code>application/json</code> too.</p>\n\n<p>The <a href=\"https://jsonfeed.org/code/\">code page</a> has also been updated with several new code libraries and apps that support JSON Feed.</p>\n",
              "date_published": "invalid-date"
            }
        """.trimIndent()

        val item = Json.decodeFromString<JsonItemInternal>(input).toJsonItem()
        assertEquals("http://jsonfeed.micro.blog/2020/08/07/json-feed-version.html", item.id)
        assertEquals("JSON Feed version 1.1", item.title)
        assertIs<HtmlContent>(item.content)
        assertNull(item.datePublished)
    }

    @Test
    fun testItemWithoutDateCorrectlyDecodes() {
        val input = """
            {
              "id": "http://jsonfeed.micro.blog/2020/08/07/json-feed-version.html",
              "title": "JSON Feed version 1.1",
              "content_html": "<p>We&rsquo;ve updated the spec to <a href=\"https://jsonfeed.org/version/1.1\">version 1.1</a>. It’s a minor update to JSON Feed, clarifying a few things in the spec and adding a couple new fields such as <code>authors</code> and <code>language</code>.</p>\n\n<p>For version 1.1, we&rsquo;re starting to move to the more specific MIME type <code>application/feed+json</code>. Clients that parse HTML to discover feeds should prefer that MIME type, while still falling back to accepting <code>application/json</code> too.</p>\n\n<p>The <a href=\"https://jsonfeed.org/code/\">code page</a> has also been updated with several new code libraries and apps that support JSON Feed.</p>\n"
            }
        """.trimIndent()

        val item = Json.decodeFromString<JsonItemInternal>(input).toJsonItem()
        assertEquals("http://jsonfeed.micro.blog/2020/08/07/json-feed-version.html", item.id)
        assertEquals("JSON Feed version 1.1", item.title)
        assertIs<HtmlContent>(item.content)
        assertNull(item.datePublished)
    }

    @Test
    fun testItemWithoutContentFails() {
        val input = """
            {
              "id": "http://jsonfeed.micro.blog/2020/08/07/json-feed-version.html",
              "title": "JSON Feed version 1.1"
            }
        """.trimIndent()

        assertFailsWith<FeedParsingException> {
            Json.decodeFromString<JsonItemInternal>(input).toJsonItem()
        }
    }

    @Test
    fun testItemWithHtmlContentCorrectlyDecodes() {
        val input = """
            {
              "id": "http://jsonfeed.micro.blog/2020/08/07/json-feed-version.html",
              "title": "JSON Feed version 1.1",
              "content_html": "<b>HTML content</b>"
            }
        """.trimIndent()

        val item = Json.decodeFromString<JsonItemInternal>(input).toJsonItem()
        assertIs<HtmlContent>(item.content)
        assertEquals("<b>HTML content</b>", item.content.useHtml)
        assertNull(item.content.useText)
    }

    @Test
    fun testItemWithTextContentCorrectlyDecodes() {
        val input = """
            {
              "id": "http://jsonfeed.micro.blog/2020/08/07/json-feed-version.html",
              "title": "JSON Feed version 1.1",
              "content_text": "some text"
            }
        """.trimIndent()

        val item = Json.decodeFromString<JsonItemInternal>(input).toJsonItem()
        assertIs<TextContent>(item.content)
        assertEquals("some text", item.content.useText)
        assertNull(item.content.useHtml)
    }

    @Test
    fun testItemWithBothTextAndHtmlContentCorrectlyDecodes() {
        val input = """
            {
              "id": "http://jsonfeed.micro.blog/2020/08/07/json-feed-version.html",
              "title": "JSON Feed version 1.1",
              "content_text": "some text",
              "content_html": "<b>HTML content</b>"
            }
        """.trimIndent()

        val item = Json.decodeFromString<JsonItemInternal>(input).toJsonItem()
        assertIs<TextAndHtmlContent>(item.content)
        assertEquals("some text", item.content.useText)
        assertEquals("<b>HTML content</b>", item.content.useHtml)
    }
}
