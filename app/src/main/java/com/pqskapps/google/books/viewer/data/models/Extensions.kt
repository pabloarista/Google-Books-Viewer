package com.pqskapps.google.books.viewer.data.models

import com.pqskapps.google.books.viewer.data.models.api.Item
import com.pqskapps.google.books.viewer.data.models.api.Root
import com.pqskapps.google.books.viewer.data.models.api.VolumeInfo

fun Root?.toBooks(): List<Book> {
    val books = mutableListOf<Book>()
    if(this != null) {
        for (item: Item in this.items.filterNotNull()) {
            val volumeInfo: VolumeInfo? = item.volumeInfo
            val book = Book(
                volumeInfo?.imageLinks?.smallThumbnail?: ""
                , volumeInfo?.title?: ""
                , volumeInfo?.authors?: listOf<String>()
                , volumeInfo?.publishedDate?: "Unknown"
                , volumeInfo?.description?: ""
                , item.accessInfo?.webReaderLink?: "")
            books.add(book)
        }//for
    }//if
    return books
}