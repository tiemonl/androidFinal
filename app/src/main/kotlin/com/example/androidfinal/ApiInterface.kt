package com.example.androidfinal

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiInterface {
    @GET("articles/")
    fun getAllArticles(): Call<ArrayList<Article>>

    @GET("authors/{authorId}")
    fun getTripDetails(@Path("authorId") authorId: Int): Call<ArticleAuthor>
}