package com.pqskapps.google.books.viewer

import android.app.Application
import com.pqskapps.google.books.viewer.data.repositories.GoogleBookRepository
import com.pqskapps.google.books.viewer.data.viewmodels.BooksViewModel

class GoogleBooksViewerApplication: Application() {
    companion object {
        val booksViewModel = BooksViewModel(GoogleBookRepository())
    }
}