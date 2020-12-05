package com.pqskapps.google.books.viewer.data.models

import java.util.*

class Book(val thumbnail: String?, val title: String?, val authors: List<String>, val publishDate: String?, val description: String?, val link: String?) {
    override fun toString(): String {
        return this.title?: ""
    }
}