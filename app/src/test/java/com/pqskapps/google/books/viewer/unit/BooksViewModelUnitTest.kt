package com.pqskapps.google.books.viewer.unit

import com.pqskapps.google.books.viewer.data.models.Book
import com.pqskapps.google.books.viewer.data.repositories.MockBookRepository
import com.pqskapps.google.books.viewer.data.viewmodels.BooksViewModel
import com.pqskapps.google.books.viewer.data.viewmodels.BooksViewModelTest
import org.junit.Test
import org.junit.Assert.*

class BooksViewModelUnitTest {
    private val bookRepository = MockBookRepository()
    private val viewModel = BooksViewModelTest(bookRepository)

    @Test
    fun executeSearchTest() {
        var query = "title"
        this.executeSearchTest(query, this.bookRepository.data)
        query = "1"
        this.executeSearchTest(query, this.bookRepository.data.filter { it.title!!.contains(query)})
        query = "title 2"
        this.executeSearchTest(query, this.bookRepository.data.filter { it.title == query })
    }
    private fun executeSearchTest(query: String, expectedResult: List<Book>) {
        this.viewModel.searchItem = query

        while(this.viewModel.hasMoreResults) {
            this.viewModel.executeSearch()
        }//while

        assertEquals(expectedResult.size, this.viewModel.resultSet.size)
        assertArrayEquals(expectedResult.toTypedArray(), this.viewModel.resultSet.toTypedArray())
        assertEquals(expectedResult.size, this.viewModel.currentIndex)
    }
}