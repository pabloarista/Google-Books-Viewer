package com.pqskapps.google.books.viewer.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.CookieManager
import android.webkit.WebView
import android.webkit.WebViewClient
import com.pqskapps.google.books.viewer.GoogleBooksViewerApplication
import com.pqskapps.google.books.viewer.R
import com.pqskapps.google.books.viewer.data.viewmodels.BooksViewModel
import java.net.URL

class ReaderFragment : Fragment() {
    private val viewModel: BooksViewModel = GoogleBooksViewerApplication.booksViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_reader, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val webView: WebView = view.findViewById<WebView>(R.id.reader_web_view)
        webView.webViewClient = WebViewClient()
        webView.settings.javaScriptEnabled = true
        val manager: CookieManager = CookieManager.getInstance()
        if(android.os.Build.VERSION.SDK_INT >= 21) {
            manager.setAcceptThirdPartyCookies(webView, true)
        } else {
            manager.setAcceptCookie(true)
        }//else
        webView.loadUrl(viewModel.selectedBook!!.link!!)
//        webView.isVerticalScrollBarEnabled = true
//        webView.isHorizontalScrollBarEnabled = true

        super.onViewCreated(view, savedInstanceState)
    }
}