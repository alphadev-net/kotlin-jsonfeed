package net.alphadev.jsonfeed.format

import kotlinx.datetime.format

internal fun JsonItem.toJsonItemInternal() = JsonItemInternal(
    id = id,
    url = url,
    externalUrl = externalUrl,
    title = title,
    contentText = content.useText,
    contentHtml = content.useHtml,
    summary = summary,
    image = image,
    bannerImage = bannerImage,
    datePublished = datePublished?.format(dateFormatter),
    dateModified = dateModified?.format(dateFormatter),
    authors = authors,
    tags = tags,
    language = language,
    attachments = attachments
)
