package net.alphadev.jsonfeed.exporter

import net.alphadev.jsonfeed.format.JsonFeed
import net.alphadev.jsonfeed.format.JsonFeedInternal
import net.alphadev.jsonfeed.format.JsonItem
import net.alphadev.jsonfeed.format.toJsonItemInternal

internal fun JsonFeed.toJsonFeedInternal() = JsonFeedInternal(
    version = version,
    title = title,
    homePageUrl = homePageUrl,
    feedUrl = feedUrl,
    description = description,
    userComment = userComment,
    nextUrl = nextUrl,
    icon = icon,
    favicon = favicon,
    v1author = null,
    authors = authors,
    language = language,
    expired = expired,
    items = items.map(JsonItem::toJsonItemInternal)
)
