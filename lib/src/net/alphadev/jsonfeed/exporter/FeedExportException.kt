package net.alphadev.jsonfeed.exporter

import java.lang.Exception

public class FeedExportException internal constructor(
    message: String? = null,
    cause: Throwable? = null
): Exception(message, cause)
