package com.example.androidfinal

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity(), ArticleItemAdapter.ArticleClickListener {


    var articlesData: List<Article> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView: RecyclerView = findViewById(R.id.rv_list_articles)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        val apiInterface: ApiInterface = ApiClient().getApiClient()!!.create(ApiInterface::class.java)
        apiInterface.getAllArticles().enqueue(object : Callback<List<Article>> {
            override fun onResponse(call: Call<List<Article>>, response: Response<List<Article>>) {
                articlesData = response.body()!!
                recyclerView.adapter = ArticleItemAdapter(response.body()!!, this@MainActivity)
            }

            override fun onFailure(call: Call<List<Article>>?, t: Throwable?) {
            }
        })

    }

    override fun getItem(position: Int) {
            Toast.makeText(this@MainActivity, articlesData[position].title, Toast.LENGTH_SHORT).show()
    }
}
