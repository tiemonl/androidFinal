package com.example.androidfinal

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.post_item_layout.view.*

class PostItemAdapter(val postList: List<Post>, val context: Context) :
    RecyclerView.Adapter<PostItemAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
            LayoutInflater.from(context).inflate(R.layout.post_item_layout,
            parent, false))
    }

    override fun getItemCount(): Int {
        return postList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.itemView.txtPostTitle.text = postList[position].title

    }
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view)
}