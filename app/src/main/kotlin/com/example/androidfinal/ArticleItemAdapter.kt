package com.example.androidfinal

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ArticleItemAdapter(
    private val articleList: List<Article>,
    private val clickListener: ArticleClickListener
) :
    RecyclerView.Adapter<ArticleItemAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        LayoutInflater.from(parent.context).inflate(R.layout.article_item_layout, parent, false).let {
            ViewHolder(it, clickListener)
        }

    override fun getItemCount() = articleList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        articleList.getOrNull(position)?.also {
            holder.articleTitleView.text = it.title
            holder.articlePageView.text = "Pages: ${it.pages}"
            holder.articleTitleView.setOnClickListener {
                clickListener.getItem(position)
            }
            holder.articlePageView.setOnClickListener{
                clickListener.getItem(position)
            }
        }
    }

    interface ArticleClickListener {
        fun getItem(position: Int)
    }

    class ViewHolder(itemView: View, var itemClick: ArticleClickListener) : RecyclerView.ViewHolder(itemView) {
        val articleTitleView: TextView = itemView.findViewById(R.id.txtArticleTitle)
        val articlePageView: TextView = itemView.findViewById(R.id.txtArticlePages)
    }
}