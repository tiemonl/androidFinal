package com.example.androidfinal

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ArticleAuthorActivity : AppCompatActivity() {
    private lateinit var author: ArticleAuthor

    private lateinit var authorName: TextView
    private lateinit var authorUserName: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_article_author)
        author = intent.getSerializableExtra("author") as ArticleAuthor
        authorName= findViewById(R.id.articleAuthor)
        authorName.text = author.title

        authorUserName = findViewById(R.id.articleAuthorUserName)
        authorUserName.text = author.username

        val fab: View = findViewById(R.id.fab)
        fab.setOnClickListener {
            showAddItemDialog(this)
        }
    }

    private fun showAddItemDialog(c: Context) {
        val apiInterface: ApiInterface = ApiClient().getApiClient()!!.create(ApiInterface::class.java)
        val taskEditText = EditText(c)
        val dialog = AlertDialog.Builder(c)
            .setTitle("Edit User name")
            .setMessage("new user name:")
            .setView(taskEditText)
            .setPositiveButton("OK") { _, _ ->
                author.username = taskEditText.text.toString()
                authorUserName.text = taskEditText.text.toString()
                apiInterface.updateAuthorDetails(author.id, author).enqueue(object: Callback<ArticleAuthor>{
                    override fun onFailure(call: Call<ArticleAuthor>, t: Throwable) {
                        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                    }

                    override fun onResponse(call: Call<ArticleAuthor>, response: Response<ArticleAuthor>) {
                        Log.i(">>>", "post submitted to API." + response.body()!!)
                        if (response.isSuccessful) {

                            Log.i(">>>", "post registration to API" + response.body()!!.toString())
                            Log.i(">>>", "post title to API" + response.body()!!.title)
                            Log.i(">>>", "post username to API" + response.body()!!.username)

                        }
                    }
                })
            }
            .setNegativeButton("Cancel", null)
            .create()
        dialog.show()
    }

}
