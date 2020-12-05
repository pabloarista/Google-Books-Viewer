package com.pqskapps.google.books.viewer.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
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
        view.findViewById<TextView>(R.id.details_title).text = book.title
        val authors = if(book.authors.isEmpty()) "Unknown" else book.authors.joinToString()
        val authorsText = "Author(s):\t${authors}"
        view.findViewById<TextView>(R.id.details_authors).text = authorsText
        val publishDate = "Publish Date:\t${book.publishDate}"
        view.findViewById<TextView>(R.id.details_publish_date).text = publishDate
        view.findViewById<TextView>(R.id.details_description).text = book.description
        view.findViewById<TextView>(R.id.details_view_book).setOnClickListener(this)
        super.onViewCreated(view, savedInstanceState)
    }
    override fun onClick(v: View?) {
        AlertDialog.Builder(this.requireActivity()).setPositiveButton(android.R.string.ok, null).setTitle("view book").setMessage("view book!").show()
    }
}