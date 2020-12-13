package com.pqskapps.google.books.viewer.data.models

import com.pqskapps.google.books.viewer.data.models.api.Item
import com.pqskapps.google.books.viewer.data.models.api.Root
import com.pqskapps.google.books.viewer.data.models.api.VolumeInfo

const val HTTP: String = "http:"
fun Root?.toBooks(): List<Book> {
    val books = mutableListOf<Book>()
    if(this != null) {
        for (item: Item in this.items.filterNotNull()) {
            val volumeInfo: VolumeInfo? = item.volumeInfo
            val book = Book(
                    volumeInfo?.imageLinks?.smallThumbnail?.toHttps()?: ""
                    , volumeInfo?.title?: ""
                    , volumeInfo?.authors?: listOf<String>()
                    , volumeInfo?.publishedDate?: "Unknown"
                    , volumeInfo?.description?: ""
                    , item.accessInfo?.webReaderLink?.toHttps()?: ""
                    , item.accessInfo?.accessViewStatus != "NONE")
            books.add(book)
        }//for
    }//if
    return books
}
fun String?.toHttps(): String? {
    var str: String? = this

    if(!str.isNullOrBlank() && str.startsWith(HTTP, true)) {
        str = "https" + str.substring(str.indexOf(HTTP) + HTTP.length - 1)
    }//if
    return str
}