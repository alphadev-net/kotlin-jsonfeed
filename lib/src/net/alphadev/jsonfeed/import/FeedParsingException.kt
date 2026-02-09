package net.alphadev.jsonfeed.import

import java.lang.Exception

public class FeedParsingException internal constructor(
    message: String? = null,
    cause: Throwable? = null
): Exception(message, cause)
