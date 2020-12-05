package com.pqskapps.google.books.viewer.unit

import com.pqskapps.google.books.viewer.data.models.Book
import com.pqskapps.google.books.viewer.data.models.api.*
import com.pqskapps.google.books.viewer.data.models.toBooks
import org.junit.Test
import org.junit.Assert.*
class BookUnitTest {
    @Test
    fun toBooksNoItemsTest() {
        var root: Root? = null

        var actual: List<Book> = root.toBooks()

        val expected = 0
        var actualSize = actual.size
        assertEquals(expected, actualSize)

        root = Root(0, listOf<Item>())

        actual = root.toBooks()

        actualSize = actual.size
        assertEquals(expected, actualSize)

        root = Root(1, listOf<Item?>(null))

        actual = root.toBooks()

        actualSize = actual.size
        assertEquals(expected, actualSize)
    }
    @Test
    fun toBooksSingelItemTest() {
        var item = Item(null, null, null)
        var root = Root(1, listOf(item))

        var actual: List<Book> = root.toBooks()

        val expected = 1
        assertEquals(expected, actual.size)

        val expectedString = ""
        var book = actual.first()

        assertEquals(expectedString, book.thumbnail)
        assertEquals(expectedString, book.title)
        assertEquals(expectedString, book.publishDate)
        assertEquals(expectedString, book.description)
        assertEquals(expectedString, book.link)
        assertEquals(listOf<String>(), book.authors)

        val thumbnail = "myThumbnail"
        val title = "myTitle"
        val authors = listOf("myAuthor1", "myAuthor2")
        val publishDate = "2020-01-01"
        val description = "myDescription"
        val link = "myLink"

        item = Item(ImageLinks(thumbnail), VolumeInfo(title, authors, publishDate, description), AccessInfo(link))
        root = Root(1, listOf(item))

        actual = root.toBooks()

        assertEquals(expected, actual.size)

        book = actual.first()

        assertEquals(thumbnail, book.thumbnail)
        assertEquals(title, book.title)
        assertEquals(authors.size, book.authors.size)
        assertEquals(authors.first(), book.authors.first())
        assertEquals(authors.last(), book.authors.last())
        assertEquals(publishDate, book.publishDate)
        assertEquals(description, book.description)
        assertEquals(link, book.link)

    }
}