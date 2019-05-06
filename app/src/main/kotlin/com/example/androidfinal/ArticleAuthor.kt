package com.example.androidfinal

import java.io.Serializable

data class ArticleAuthor(
    var id: Int,
    var title: String,
    var username: String
) : Serializable