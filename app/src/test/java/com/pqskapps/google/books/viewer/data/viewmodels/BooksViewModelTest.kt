package com.pqskapps.google.books.viewer.data.viewmodels

import com.pqskapps.google.books.viewer.data.repositories.BookRepository

class BooksViewModelTest(booksRepository: BookRepository): BooksViewModel(booksRepository) {
    override fun executeSearch() {
        this.queryBooks()
    }
}