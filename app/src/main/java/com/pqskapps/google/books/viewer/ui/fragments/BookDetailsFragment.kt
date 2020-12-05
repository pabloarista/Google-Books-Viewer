package com.pqskapps.google.books.viewer.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.pqskapps.google.books.viewer.GoogleBooksViewerApplication
import com.pqskapps.google.books.viewer.R
import com.pqskapps.google.books.viewer.data.models.Book
import com.pqskapps.google.books.viewer.data.viewmodels.BooksViewModel

class BookDetailsFragment : Fragment(), View.OnClickListener {
    private val viewModel: BooksViewModel = GoogleBooksViewerApplication.booksViewModel
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_book_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val book: Book = this.viewModel.selectedBook!!

        view.findViewById<ImageView>(R.id.details_thumbnail).load(book.thumbnail)
        val title = "Title:\t\"${book.title}\""
        view.findViewById<TextView>(R.id.details_title).text = title
        val authors = if(book.authors.isEmpty()) "Unknown" else book.authors.joinToString()
        val authorsText = "Author(s):\t${authors}"
        view.findViewById<TextView>(R.id.details_authors).text = authorsText
        val publishDate = "Publish Date:\t${book.publishDate}"
        view.findViewById<TextView>(R.id.details_publish_date).text = publishDate
        view.findViewById<TextView>(R.id.details_description).text = book.description
        val textViewViewBook: TextView = view.findViewById<TextView>(R.id.details_view_book)
        if(!book.canRead) {
            textViewViewBook.text = getString(R.string.details_link_unavailable_title)
        }//if
        textViewViewBook.setOnClickListener(this)
        super.onViewCreated(view, savedInstanceState)
    }
    override fun onClick(v: View?) {
        val book: Book = this.viewModel.selectedBook!!
        if(book.link.isNullOrBlank()) {
            AlertDialog.Builder(v?.context!!).setPositiveButton(android.R.string.ok, null).setTitle(R.string.error).setMessage(R.string.details_no_link).show()
        } else  v?.findNavController()?.navigate(R.id.segue_reader)
    }
}