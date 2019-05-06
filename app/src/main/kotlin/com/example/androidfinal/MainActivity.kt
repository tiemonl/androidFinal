package com.example.androidfinal

import android.content.Intent
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


    var articlesData: ArrayList<Article> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView: RecyclerView = findViewById(R.id.rv_list_articles)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        val apiInterface: ApiInterface = ApiClient().getApiClient()!!.create(ApiInterface::class.java)
        apiInterface.getAllArticles().enqueue(object : Callback<ArrayList<Article>> {
            override fun onResponse(call: Call<ArrayList<Article>>, response: Response<ArrayList<Article>>) {
                articlesData = response.body()!!
                recyclerView.adapter = ArticleItemAdapter(response.body()!!, this@MainActivity)
            }

            override fun onFailure(call: Call<ArrayList<Article>>?, t: Throwable?) {
            }
        })
        Toast.makeText(this, "Hello world", Toast.LENGTH_LONG).show()
    }

    override fun getItem(position: Int) {
        val intent = Intent(this, ArticleAuthorActivity::class.java)

        val apiInterface: ApiInterface = ApiClient().getApiClient()!!.create(ApiInterface::class.java)
        apiInterface.getAuthorDetails(articlesData[position].authorId).enqueue(object : Callback<ArticleAuthor>{
            override fun onResponse(call: Call<ArticleAuthor>, response: Response<ArticleAuthor>) {
                val author: ArticleAuthor = response.body()!!
                intent.putExtra("author", author)
                startActivity(intent)
            }

            override fun onFailure(call: Call<ArticleAuthor>, t: Throwable) {
            }

        })
    }
}
