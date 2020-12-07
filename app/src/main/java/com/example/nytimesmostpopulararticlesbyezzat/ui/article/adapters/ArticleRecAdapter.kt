package com.example.nytimesmostpopulararticlesbyezzat.ui.article.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.nytimesmostpopulararticlesbyezzat.R
import com.example.nytimesmostpopulararticlesbyezzat.data.models.ArticleItem
import kotlinx.android.synthetic.main.article_item.view.*

class ArticleRecAdapter : RecyclerView.Adapter<ArticleRecAdapter.ArticleVH>() {
    // interface
    interface OnItemClickArticle {
        fun onClick(pos: Int)
    }

    // var
    private var listArticle: List<ArticleItem> = ArrayList()
    lateinit var onItemClick: OnItemClickArticle

    fun setlistArticle(listArticle: List<ArticleItem>) {
        this.listArticle = listArticle
        notifyDataSetChanged()

    }

    fun setOnItemClickArticle(onItemClick: OnItemClickArticle) {
        this.onItemClick = onItemClick


    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleVH {
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.article_item, parent, false)
        return ArticleVH(v)
    }

    override fun onBindViewHolder(holder: ArticleVH, position: Int) {
        holder.bindArticle(listArticle.get(position))
        holder.itemView.setOnClickListener({
            onItemClick.onClick(holder.adapterPosition)
        })
    }

    override fun getItemCount(): Int {
        return listArticle.size
    }

    class ArticleVH(itemView: View) : RecyclerView.ViewHolder(itemView) {


        fun bindArticle(articleItem: ArticleItem) {
            if (articleItem != null) {
                itemView.txt_article_title_item.text = articleItem.title
                itemView.txt_article_by_item.text = "By " + articleItem.source
                itemView.txt_article_date_item.text = articleItem.published_date

                Glide.with(itemView.context)
                    .load(articleItem.media.get(0).metadata.get(0).url)
                    .into(itemView.img_article_item)


            }
        }

    }
}