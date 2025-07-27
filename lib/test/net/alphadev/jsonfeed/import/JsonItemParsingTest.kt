package net.alphadev.jsonfeed.import

import kotlinx.io.readString
import kotlinx.serialization.json.Json
import net.alphadev.jsonfeed.format.JsonFeed
import net.alphadev.jsonfeed.format.JsonItem
import net.alphadev.jsonfeed.readResource
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertIs
import kotlin.time.ExperimentalTime
import kotlin.time.Instant

class JsonItemParsingTest {
    @Test
    fun happyPathWorks() {
        val input = """
            {
              "id": "http://jsonfeed.micro.blog/2020/08/07/json-feed-version.html",
              "title": "JSON Feed version 1.1",
              "content_html": "<p>We&rsquo;ve updated the spec to <a href=\"https://jsonfeed.org/version/1.1\">version 1.1</a>. It’s a minor update to JSON Feed, clarifying a few things in the spec and adding a couple new fields such as <code>authors</code> and <code>language</code>.</p>\n\n<p>For version 1.1, we&rsquo;re starting to move to the more specific MIME type <code>application/feed+json</code>. Clients that parse HTML to discover feeds should prefer that MIME type, while still falling back to accepting <code>application/json</code> too.</p>\n\n<p>The <a href=\"https://jsonfeed.org/code/\">code page</a> has also been updated with several new code libraries and apps that support JSON Feed.</p>\n",
              "date_published": "2020-08-07T11:44:36-05:00",
              "url": "https://www.jsonfeed.org/2020/08/07/json-feed-version.html"
            }
        """.trimIndent()

        val item = Json.decodeFromString<JsonItem>(input)
        assertEquals("http://jsonfeed.micro.blog/2020/08/07/json-feed-version.html", item.id)
        assertEquals("JSON Feed version 1.1", item.title)
        assertEquals("<p>We&rsquo;ve updated the spec to <a href=\"https://jsonfeed.org/version/1.1\">version 1.1</a>. It’s a minor update to JSON Feed, clarifying a few things in the spec and adding a couple new fields such as <code>authors</code> and <code>language</code>.</p>\n\n<p>For version 1.1, we&rsquo;re starting to move to the more specific MIME type <code>application/feed+json</code>. Clients that parse HTML to discover feeds should prefer that MIME type, while still falling back to accepting <code>application/json</code> too.</p>\n\n<p>The <a href=\"https://jsonfeed.org/code/\">code page</a> has also been updated with several new code libraries and apps that support JSON Feed.</p>\n", item.contentHtml)
        assertEquals(Instant.parse("2020-08-07T11:44:36-05:00"), item.datePublished)
        assertEquals("https://www.jsonfeed.org/2020/08/07/json-feed-version.html", item.url)
    }
}
