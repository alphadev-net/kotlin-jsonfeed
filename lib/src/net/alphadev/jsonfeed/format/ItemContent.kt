package net.alphadev.jsonfeed.format

public sealed interface ItemContent

public data class TextContent(val text: String): ItemContent

public data class HtmlContent(val html: String): ItemContent

public data class TextAndHtmlContent(val text: String, val html: String): ItemContent

public val ItemContent.useText: String?
    get() = when (this) {
        is TextContent -> text
        is TextAndHtmlContent -> text
        else -> null
    }

public val ItemContent.useHtml: String?
    get() = when (this) {
        is HtmlContent -> html
        is TextAndHtmlContent -> html
        else -> null
    }
