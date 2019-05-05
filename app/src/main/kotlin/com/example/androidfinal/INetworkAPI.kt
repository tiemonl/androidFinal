package com.example.androidfinal

import io.reactivex.Observable
import retrofit2.http.GET

interface INetworkAPI {
    @GET("articles/")
    fun getAllArticles(): Observable<List<Post>>
}