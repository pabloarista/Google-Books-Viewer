package com.pqskapps.google.books.viewer.ui.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.ProgressBar
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat.getSystemService
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.pqskapps.google.books.viewer.GoogleBooksViewerApplication
import com.pqskapps.google.books.viewer.R
import com.pqskapps.google.books.viewer.data.viewmodels.BooksViewModel


class SearchFragment : Fragment(), View.OnClickListener, View.OnFocusChangeListener {
    private lateinit var editTextSearch: EditText
    private lateinit var layoutControls: LinearLayout
    private lateinit var progressBar: ProgressBar
    private val viewModel: BooksViewModel = GoogleBooksViewerApplication.booksViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val btn: Button = view.findViewById(R.id.btnSearch)
        btn.setOnClickListener(this)
        this.editTextSearch = view.findViewById(R.id.editTextSearch)
        this.editTextSearch.setOnFocusChangeListener(this)
        this.layoutControls = view.findViewById(R.id.layoutControls)
        this.progressBar = view.findViewById(R.id.progressBar)
//        this.viewModel = (activity?.application as GoogleBooksViewerApplication).booksViewModel
        this.viewModel.callback = ::navigateToResults
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onClick(v: View?) {
        val query = "" + this.editTextSearch.text
        if(query.isBlank()) {
            AlertDialog.Builder(v!!.context).setMessage(R.string.error_empty_query).setTitle(R.string.error)
                .setPositiveButton(android.R.string.ok, null).show()
        } else {
            this.layoutControls.visibility = View.GONE
            this.progressBar.visibility = View.VISIBLE
            this.viewModel.searchItem = query
            this.viewModel.executeSearch()
        }//else
    }

    override fun onFocusChange(v: View?, hasFocus: Boolean) {
        //if the edit text loses focus, we want to hide the keyboard. othewise it's visible while loading
        if(v == this.editTextSearch && !hasFocus) {
            val inputMethodManager = requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(v.windowToken, 0)
        }
    }
    private fun navigateToResults() {
        this.editTextSearch.findNavController().navigate(R.id.segue_results)
        this.layoutControls.visibility = View.VISIBLE
        this.progressBar.visibility = View.GONE
    }
}