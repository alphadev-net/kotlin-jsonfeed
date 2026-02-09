package net.alphadev.jsonfeed.format

import net.alphadev.jsonfeed.import.FeedParsingException
import kotlinx.datetime.parse
import kotlin.time.ExperimentalTime
import kotlin.time.Instant

@ExperimentalTime
internal fun JsonItemInternal.toJsonItem() = JsonItem(
    id = id ?: throw FeedParsingException("JsonItem needs the id Field to be set"),
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

@ExperimentalTime
private fun parseDate(input: String?) = try {
    input?.let { Instant.parse(it, dateFormatter) }
} catch (_: Throwable) {
    null
}
