package com.example.androidfinal

import com.google.gson.annotations.SerializedName

data class Post(
    @SerializedName("authorId") val authorId: Int,
    @SerializedName("id") val id: Int,
    @SerializedName("title") val title: String
)