package com.example.androidfinal

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import java.net.URL

class MainActivity : AppCompatActivity() {

    private lateinit var button: Button
    private lateinit var txtview: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        button = findViewById<Button>(R.id.button)
        button.setOnClickListener {
            update()
        }
    }

    private fun update() {
        doAsync {
            //Execute all the lon running tasks here

            val apiResponse = URL("https://my-json-server.typicode.com/tiemonl/androidFinal/authors").readText()
            uiThread {
                //Update the UI thread here
                txtview = findViewById(R.id.textview)
                txtview.text = apiResponse
            }
        }

    }
}
