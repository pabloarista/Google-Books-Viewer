package com.pqskapps.google.books.viewer.data.repositories

import android.text.TextUtils
import com.google.gson.Gson
import com.pqskapps.google.books.viewer.data.models.Book
import com.pqskapps.google.books.viewer.data.models.api.Root
import com.pqskapps.google.books.viewer.data.models.toBooks
import okhttp3.OkHttpClient
import okhttp3.Request

/**
 * Google Book repository of Books
 */
class GoogleBookRepository(): BookRepository {
    /**
     * [query] to filter result set
     * [startIndex] to begin filter from
     */
    override fun getBooks(query: String, startIndex: Int): List<Book> {
        if(query.isBlank()) return listOf<Book>()

        //build the http request
        val client = OkHttpClient()

        val request = Request.Builder()
            .url("https://www.googleapis.com/books/v1/volumes?q=${query}&startIndex=${startIndex}&maxResults=${MAX_RESULTS}")
            .build()

        val response = client.newCall(request).execute()
        val googleBooks: Root?
        response.use {
            val json = it.body()?.string()
            googleBooks = if(json != null) Gson().fromJson(json, Root::class.java)
            else null
        }//use

        //transform the json response into our Book model
        val books: List<Book> = googleBooks.toBooks()

        return books
    }
    companion object {
        const val MAX_RESULTS = 20
    }
}