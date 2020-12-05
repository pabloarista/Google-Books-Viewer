package com.pqskapps.google.books.viewer.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.navigation.findNavController
import com.pqskapps.google.books.viewer.GoogleBooksViewerApplication
import com.pqskapps.google.books.viewer.R
import com.pqskapps.google.books.viewer.data.viewmodels.BooksViewModel

/**
 * A fragment representing a list of Items.
 */
class SearchResultsFragment : Fragment(), View.OnClickListener {
    private val viewModel: BooksViewModel = GoogleBooksViewerApplication.booksViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_search_results_list, container, false)

        val fragment = this
        // Set the adapter
        if (view is RecyclerView) {
            with(view) {
                layoutManager = LinearLayoutManager(context)
                adapter = MyBookRecyclerViewAdapter(viewModel.resultSet, fragment, ::loadMoreEntries, viewModel.hasMoreResults)
            }
            this.viewModel.callback = {
                view.adapter?.notifyDataSetChanged()
                (view.adapter as MyBookRecyclerViewAdapter).hasMoreEntries = viewModel.hasMoreResults
            }
        }
        return view
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onClick(v: View?) {
        val index = v!!.tag as Int
        this.viewModel.selectedBook = this.viewModel.resultSet[index]

        v.findNavController().navigate(R.id.segue_details)
    }
    private fun loadMoreEntries() {
        this.viewModel.executeSearch()
    }
}