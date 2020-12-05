package com.pqskapps.google.books.viewer.unit

import com.pqskapps.google.books.viewer.data.repositories.MockBookRepository
import org.junit.Test
import org.junit.Assert.*

class BookRepositoryUnitTest {
    private val repository = MockBookRepository()
    @Test
    fun getBooksInvalidQueryTest() {
        var query = ""

        var actual = this.repository.getBooks(query)

        assertEquals(0, actual.size)

        query = "   "

        actual = this.repository.getBooks(query)
        assertEquals(0, actual.size)
    }
    @Test
    fun getBooksFilter1Test() {
        val query = "1"

        var actual = this.repository.getBooks(query)

        assertEquals(2, actual.size)

        actual = this.repository.getBooks(query, 1)

        assertEquals(1, actual.size)
    }
    @Test
    fun getBooksNoResultsTest() {
        val query = "x"

        val actual = this.repository.getBooks(query)

        assertEquals(0, actual.size)
    }
}