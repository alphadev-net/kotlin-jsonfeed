package net.alphadev.jsonfeed.import

import net.alphadev.jsonfeed.format.JsonAuthor
import net.alphadev.jsonfeed.format.JsonFeed
import net.alphadev.jsonfeed.format.JsonFeed11
import net.alphadev.jsonfeed.format.JsonFeedInternal

internal fun JsonFeedInternal.toJsonFeed() = JsonFeed(
    version = JsonFeed11,
    title = title,
    homePageUrl = homePageUrl,
    feedUrl = feedUrl,
    description = description,
    userComment = userComment,
    nextUrl = nextUrl,
    icon = icon,
    favicon = favicon,
    authors = buildList {
        if (v1author != null && !authors.any { it.name == v1author }) {
            add(JsonAuthor(name = v1author))
        }

        addAll(authors)
    },
    language = language?.let {
        when (version) {
            JsonFeed11 -> it
            else -> throw FeedParsingException("JsonFeed v1.1 attribute language detected in JsonFeed with version $version")
        }
    },
    expired = expired,
    items = items
)
