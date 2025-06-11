package net.alphadev.jsonfeed.demo

import net.alphadev.jsonfeed.format.JsonFeed
import net.alphadev.jsonfeed.import.parseJsonFeed
import okhttp3.OkHttpClient
import okhttp3.Request

fun main() {
    val feedData = loadFeedContents()
    val feed = parseJsonFeed(feedData ?: return)
    printFeedOverview(feed ?: return)
}

private fun loadFeedContents(): String? {
    val client = OkHttpClient()

    val request = Request.Builder()
        .url("https://jan.alphadev.net/feed.json")
        .build()

    val response = client.newCall(request).execute()

    return response.body?.string()
}

private fun printFeedOverview(feed: JsonFeed) {
    println(feed.title)
    println(feed.description)

    feed.items.forEach { item ->
        println("${item.title} (${item.url})")
    }
}
