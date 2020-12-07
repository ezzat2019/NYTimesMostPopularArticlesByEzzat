package com.example.nytimesmostpopulararticlesbyezzat.ui.article

import android.content.Intent
import android.net.ConnectivityManager
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.nytimesmostpopulararticlesbyezzat.R
import com.example.nytimesmostpopulararticlesbyezzat.data.models.ResultArcticleModel
import com.example.nytimesmostpopulararticlesbyezzat.ui.article.adapters.ArticleRecAdapter
import com.example.nytimesmostpopulararticlesbyezzat.ui.article.viewmodel.ArticleViewModel
import com.example.nytimesmostpopulararticlesbyezzat.ui.article_detail.ArticleDetailActivity
import com.example.nytimesmostpopulararticlesbyezzat.utills.ConstantHelper
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_article.*

@AndroidEntryPoint
class ArticleActivity : AppCompatActivity() {


    // var
    private lateinit var articleViewModel: ArticleViewModel
    private lateinit var articleAdapter: ArticleRecAdapter
    private lateinit var resultArcticleModel: ResultArcticleModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_article)


        if (checkInternetConn()) {
            init()
            observeArticleApi()
            actions()
        } else {
            Toast.makeText(applicationContext, "check your internet connection", Toast.LENGTH_SHORT)
                .show()
        }

    }

    private fun actions() {
        articleAdapter.setOnItemClickArticle(object : ArticleRecAdapter.OnItemClickArticle {
            override fun onClick(pos: Int) {
                val intent: Intent = Intent(applicationContext, ArticleDetailActivity::class.java)
                    .apply {
                        this.putExtra(
                            ConstantHelper.INTENT_ARTICLE_DETAIL, resultArcticleModel
                                .results.get(pos)
                        )
                    }

                startActivity(intent)
            }

        })
    }

    private fun observeArticleApi() {
        spin_kit.visibility = View.VISIBLE
        articleViewModel.getArticle(getString(R.string.key))
            .observe(this, object : Observer<ResultArcticleModel> {
                override fun onChanged(t: ResultArcticleModel?) {

                    if (t != null) {
                        resultArcticleModel = t
                        articleAdapter.setlistArticle(t.results)
                    }

                    spin_kit.visibility = View.GONE

                }

            })

    }

    private fun checkInternetConn(): Boolean {
        val con: ConnectivityManager = getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager
        return con.activeNetworkInfo != null
    }

    private fun init() {
        articleViewModel = ViewModelProvider(this).get(ArticleViewModel::class.java)
        spin_kit.visibility = View.GONE

        buildRecycle()
    }

    private fun buildRecycle() {
        rec_article.setHasFixedSize(true)
        rec_article.layoutManager = LinearLayoutManager(applicationContext)

        articleAdapter = ArticleRecAdapter()
        rec_article.adapter = articleAdapter

    }


}