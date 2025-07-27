package net.alphadev.jsonfeed.format

sealed interface ItemContent

data class TextContent(val text: String): ItemContent

data class HtmlContent(val html: String): ItemContent

data class TextAndHtmlContent(val text: String, val html: String): ItemContent

val ItemContent.useText: String?
    get() = when (this) {
        is TextContent -> text
        is TextAndHtmlContent -> text
        else -> null
    }

val ItemContent.useHtml: String?
    get() = when (this) {
        is HtmlContent -> html
        is TextAndHtmlContent -> html
        else -> null
    }
