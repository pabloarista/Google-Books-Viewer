package com.pqskapps.google.books.viewer.data.viewmodels

import androidx.lifecycle.ViewModel
import com.pqskapps.google.books.viewer.data.models.Book
import com.pqskapps.google.books.viewer.data.repositories.BookRepository
import com.pqskapps.google.books.viewer.data.repositories.GoogleBookRepository
import kotlinx.coroutines.*

/**
 * View model to handle all of the book processing
 * @property bookRepository
 */
open class BooksViewModel(private val bookRepository: BookRepository): ViewModel() {
    //query string
    var searchItem: String? = null
        set(value) {
            //if the search item changes, we reset so that the new result set will NOT mix with the old results (if any)
            if(value != field) {
                this.resultSet.clear()
                this.currentIndex = 0
                this.selectedBook = null
                this.hasMoreResults = true
            }//if
            field = value
        }
    var callback: (() -> Unit)? = null
    private var didLoadData: Boolean = false
    //complete result set from each page
    val resultSet: MutableList<Book> = mutableListOf<Book>()
    //the current index to use when tapping on the "Load More" button
    var currentIndex: Int = 0
        private set(value) { field = value }
    //the current book to display (when the user taps on an entry in the list of the result set
    var selectedBook: Book? = null
        private set(value) { field = value }

    //used for coroutines, so that we don't block the main UI thread
    private val viewModelJob = SupervisorJob()
    private val viewModelScope = CoroutineScope(Dispatchers.IO + this.viewModelJob)
    //boolean flag that will be used to show if we are busy or NOT in the UI
    var isBusy: Boolean = false
        //setter is private since this will be set internally to this view model
        private set(value) { field = value }
    //used to determine if a "Load More" should be displayed
    var hasMoreResults: Boolean = true
        private set(value) { field = value }

    /**
     * executes a query search of the search item entered if the view model is NOT busy already searching
     */
    open fun executeSearch() {
        //only start if NOT busy
        if(this.isBusy) return

        //set to busy to begin process
        this.isBusy = true

        //do NOT block the main UI thread
        this.viewModelScope.launch {
            queryBooks()
            if(!didLoadData && resultSet.isNotEmpty() && callback != null) {
                withContext(Dispatchers.Main) {
                    callback!!()
                }
            }
        }//launch
    }
    protected open fun queryBooks() {
        //query the books
        val books: List<Book> = bookRepository.getBooks(searchItem!!, currentIndex)
        if(books.isNotEmpty()) {
            //if there are any results, append to the result set and update the current index to the correct value
            resultSet.addAll(books)
            currentIndex = resultSet.size
        } else hasMoreResults = false
        isBusy = false
    }
}