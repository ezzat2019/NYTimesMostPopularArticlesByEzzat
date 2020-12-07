package com.example.nytimesmostpopulararticlesbyezzat.ui.article_detail

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.nytimesmostpopulararticlesbyezzat.R
import com.example.nytimesmostpopulararticlesbyezzat.data.models.ArticleItem
import com.example.nytimesmostpopulararticlesbyezzat.utills.ConstantHelper
import kotlinx.android.synthetic.main.activity_article_detail.*

class ArticleDetailActivity : AppCompatActivity() {

    // var
    private lateinit var articleItem: ArticleItem

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
        setContentView(R.layout.activity_article_detail)

        if (intent.hasExtra(ConstantHelper.INTENT_ARTICLE_DETAIL)) {

            supportActionBar?.setTitle("Article Detials")
            articleItem = intent.getParcelableExtra(ConstantHelper.INTENT_ARTICLE_DETAIL)
            bindData(articleItem)

            actions()

        } else {
            Toast.makeText(applicationContext, "error no data try again", Toast.LENGTH_SHORT).show()
            onBackPressed()
        }

    }

    private fun actions() {
        btn_article_title.setOnClickListener({
            if (articleItem != null) {

                val url = articleItem.ur

                startActivity(Intent(Intent.ACTION_VIEW).apply { this.data = Uri.parse(url) })

            }

        })
    }

    private fun bindData(articleItem: ArticleItem) {
        txt_article_title.text = articleItem.title
        txt_article_sub.text = articleItem.abstract


        Glide.with(applicationContext)
            .load(
                articleItem.media.get(0)
                    .metadata.get(2).url
            )
            .into(img_article_details)

    }
}