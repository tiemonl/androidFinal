package com.example.androidfinal

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.article_item_layout.view.*

class ArticleItemAdapter(val articleList: List<Article>, val clickListener: ArticleClickListener) :

    RecyclerView.Adapter<ArticleItemAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.article_item_layout, parent, false)
        return ViewHolder(view, clickListener)
    }

    override fun getItemCount(): Int {
        return articleList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.txtArticleTitle.text = articleList[position].title
    }

    interface ArticleClickListener {
        fun getItem(position: Int)
    }

    class ViewHolder(itemView: View, var itemClick: ArticleClickListener) : RecyclerView.ViewHolder(itemView) {
        var txtArticleTitle: TextView = itemView.findViewById(R.id.txtArticleTitle)
        fun bindData(articlesList: List<Article>?, position: Int) {
            txtArticleTitle.text = articlesList!![position].title
            itemView.setOnClickListener {
                itemClick.getItem(adapterPosition)
            }
        }
    }
}