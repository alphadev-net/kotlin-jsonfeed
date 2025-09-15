package net.alphadev.jsonfeed.demo

import net.alphadev.jsonfeed.format.JsonFeed
import net.alphadev.jsonfeed.import.parseJsonFeed
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.statement.bodyAsText

suspend fun main() {
    val feedData = loadFeedContents()
    val feed = parseJsonFeed(feedData ?: return)
    printFeedOverview(feed ?: return)
}

private suspend fun loadFeedContents(): String {
    val client = HttpClient()
    return client.get("https://jan.alphadev.net/feed.json")
        .bodyAsText()
}

private fun printFeedOverview(feed: JsonFeed) {
    println(feed.title)
    println(feed.description)

    feed.items.forEach { item ->
        println("${item.title} (${item.url})")
    }
}
