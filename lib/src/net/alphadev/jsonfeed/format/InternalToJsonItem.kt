package net.alphadev.jsonfeed.format

import kotlinx.datetime.parse
import net.alphadev.jsonfeed.import.FeedParsingException
import kotlin.time.Instant

fun JsonItemInternal.toJsonItem() = JsonItem(
    id = id,
    url = url,
    externalUrl =externalUrl,
    title = title,
    content = when {
        contentHtml != null && contentText != null -> TextAndHtmlContent(contentText, contentHtml)
        contentHtml != null -> HtmlContent(contentHtml)
        contentText != null -> TextContent(contentText)
        else -> throw FeedParsingException("Could not parse item with id: $id, neither content_html nor content_text is set. One or both must be provided.")
    },
    summary = summary,
    image = image,
    bannerImage = bannerImage,
    datePublished = parseDate(datePublished),
    dateModified = parseDate(dateModified),
    authors = authors,
    tags = tags,
    language = language,
    attachments = attachments
)

private fun parseDate(input: String?) = try {
    input?.let { Instant.parse(it, dateFormatter) }
} catch (_: Throwable) {
    null
}
