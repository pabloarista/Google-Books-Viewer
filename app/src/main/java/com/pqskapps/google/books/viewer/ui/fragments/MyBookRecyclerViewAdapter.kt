package com.pqskapps.google.books.viewer.ui.fragments

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import com.pqskapps.google.books.viewer.R
import com.pqskapps.google.books.viewer.data.models.Book
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.InputStream
import java.net.URL

class MyBookRecyclerViewAdapter(
    private val values: List<Book>,
    private val clickListener: View.OnClickListener
) : RecyclerView.Adapter<MyBookRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_search_results, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        val title = "\"${item.title}\""
        holder.titleView.text = title
        holder.authorsView.text = if(item.authors.isEmpty()) "Unknown" else item.authors.joinToString()
        holder.publishDateView.text = item.publishDate
        holder.thumbnailView.load(item.thumbnail)
        //we can use the tag to get the position. this way the view model can work with the onClickListener instead of the view
        holder.itemView.tag = position
        holder.itemView.setOnClickListener(clickListener)
    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val titleView: TextView = view.findViewById(R.id.item_title)
        val authorsView: TextView = view.findViewById(R.id.item_authors)
        val publishDateView: TextView = view.findViewById(R.id.item_publish_date)
        val thumbnailView: ImageView = view.findViewById(R.id.imageView
        )

        override fun toString(): String {
            return super.toString() + " '" + titleView.text + "'"
        }
    }
}