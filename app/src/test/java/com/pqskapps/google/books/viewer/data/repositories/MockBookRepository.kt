package com.pqskapps.google.books.viewer.data.repositories

import android.text.TextUtils
import com.pqskapps.google.books.viewer.data.models.Book

class MockBookRepository: BookRepository {
    val data: List<Book>
    override fun getBooks(query: String, startIndex: Int): List<Book> {
        if(query.isBlank() || startIndex + 1 > this.data.size) return listOf<Book>()

        var filter = this.data.filter{ it.title?.contains(query)?: false }
        if(startIndex + 1 > filter.size) return listOf<Book>()

        if(startIndex > 0) {
            filter = filter.drop(startIndex)
        }//if

        filter = filter.take(INTERVAL)

        return filter
    }
    init {
        val tmpData = mutableListOf<Book>()
        for(i in 1..10) {
            val book = Book("thumbnail " + i, "title " + i, listOf("author 1 - " + i, "author 2 - " + i), "2020-01-01", "some description " + i, "my linke " + i)
            tmpData.add(book)
        }
        this.data = tmpData
    }
    companion object {
        const val INTERVAL = 2
    }
}