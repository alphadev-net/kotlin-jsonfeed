package net.alphadev.sitemap.format

import kotlinx.datetime.format.DateTimeComponents
import kotlinx.datetime.serializers.FormattedInstantSerializer

object IsoDateSerializer : FormattedInstantSerializer(
    name = "net.alphadev.sitemap.format.IsoDateSerializer",
    format = DateTimeComponents.Formats.ISO_DATE_TIME_OFFSET,
)
