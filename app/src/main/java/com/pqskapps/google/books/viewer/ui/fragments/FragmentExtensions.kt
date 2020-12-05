package com.pqskapps.google.books.viewer.ui.fragments

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import android.widget.ImageView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.InputStream
import java.net.URL

fun ImageView.load(thumbnail: String?) {
    val iv = this
    //we only want to load the thumbnail if there is a URL to load
    if(!thumbnail.isNullOrBlank()) {
        //we load the thumbnail in a background thread so we do NOT freeze the main UI
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val url = URL(thumbnail)
                val bmp: Bitmap = BitmapFactory.decodeStream(url.content as InputStream)
                withContext(Dispatchers.Main) {
                    iv.setImageBitmap(bmp)
                }//withContext
            } catch (t: Throwable) {
                Log.e("ImageView.Extension", t.message ?: "Unknown error")
            }//catch
        }//launch
    }//if
}