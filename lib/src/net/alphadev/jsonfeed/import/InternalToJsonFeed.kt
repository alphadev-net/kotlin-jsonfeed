package net.alphadev.jsonfeed.import

import net.alphadev.jsonfeed.format.*
import kotlin.time.ExperimentalTime

@ExperimentalTime
internal fun JsonFeedInternal.toJsonFeed() = JsonFeed(
    version = JsonFeed11.also { if(version == null) throw FeedParsingException("JsonFeed needs a version") },
    title = title ?: throw FeedParsingException("JsonFeed needs a title"),
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
    items = items.mapNotNull {
        try {
            it.toJsonItem()
        } catch (ex: FeedParsingException) {
            null
        }
    }
)
