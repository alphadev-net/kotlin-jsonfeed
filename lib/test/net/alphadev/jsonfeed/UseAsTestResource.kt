package net.alphadev.jsonfeed

import kotlinx.io.Source
import kotlinx.io.buffered
import kotlinx.io.files.Path
import kotlinx.io.files.SystemFileSystem

inline fun readResource(
    @Suppress("SameParameterValue") fileName: String,
    process: (Source) -> Unit
) {
    val path = Path("testResources", fileName)
    val fileSource = SystemFileSystem.source(path)
    process(fileSource.buffered())
}
