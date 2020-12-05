package com.pqskapps.google.books.viewer.integration

import com.pqskapps.google.books.viewer.data.repositories.GoogleBookRepository
import org.junit.Assert.*
import org.junit.Test

class GoogleBookRepositoryIntegrationTest {
    private val repository = GoogleBookRepository()
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
    fun getBooksSpaceQueryTest() {
        /*
        the Google API does NOT always return the same result set. possibly that the order changes due to the amount of volume of requests since this is a public API.
        the API docs only mentions: "Total number of volumes found. This might be greater than the number of volumes returned in this response if results have been paginated."
        https://developers.google.com/books/docs/v1/reference/volumes/list#totalItems

        but that's NOT true as if you query without a start index, that number changes. Sometimes it's the same, but not consistent

        Due to this, this test does NOT always pass
         */
        val query = "Space"

        val actualIndex0 = this.repository.getBooks(query)

        assertEquals(GoogleBookRepository.MAX_RESULTS, actualIndex0.size)

        val lastBook = actualIndex0.last()

        val startIndex = GoogleBookRepository.MAX_RESULTS - 1
        val actualIndex19 = this.repository.getBooks(query, startIndex)

        assertEquals(GoogleBookRepository.MAX_RESULTS, actualIndex19.size)

        val firstBook = actualIndex19.first()

        val expectedTitle = lastBook.title
        val actualTitle = firstBook.title
        assertEquals(expectedTitle, actualTitle)
    }
}