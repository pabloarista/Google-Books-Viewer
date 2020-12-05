package com.pqskapps.google.books.viewer.data.repositories

import com.pqskapps.google.books.viewer.data.models.Book

/**
 * Book repository of Books
 */
interface BookRepository {
    /**
     * [query] to filter result set
     * [startIndex] to begin filter from
     */
    fun getBooks(query: String, startIndex: Int = 0): List<Book>
}