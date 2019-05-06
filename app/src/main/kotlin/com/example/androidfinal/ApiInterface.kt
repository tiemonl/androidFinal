package com.example.androidfinal

import retrofit2.Call
import retrofit2.http.*

interface ApiInterface {
    @GET("articles/")
    fun getAllArticles(): Call<ArrayList<Article>>

    @GET("authors/{authorId}")
    fun getAuthorDetails(@Path("authorId") id: Int): Call<ArticleAuthor>

    @Headers("Content-type: application/json")
    @PUT("authors/{authorId}")
    fun updateAuthorDetails(@Path("authorId") id: Int,
                            @Body author: ArticleAuthor): Call<ArticleAuthor>
}