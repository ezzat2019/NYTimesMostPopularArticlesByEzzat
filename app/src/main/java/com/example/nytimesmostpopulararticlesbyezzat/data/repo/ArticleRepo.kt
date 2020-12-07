package com.example.nytimesmostpopulararticlesbyezzat.data.repo

import com.example.nytimesmostpopulararticlesbyezzat.data.api.ArticleApi
import com.example.nytimesmostpopulararticlesbyezzat.data.api.ArticleApiImp
import com.example.nytimesmostpopulararticlesbyezzat.data.models.ResultArcticleModel
import javax.inject.Inject

class ArticleRepo : ArticleApiImp {

    lateinit var articleApi: ArticleApi

    @Inject
    constructor(articleApi: ArticleApi) {
        this.articleApi = articleApi
    }


    override suspend fun getArticle(key: String): ResultArcticleModel {
        return articleApi.getArticle(key)
    }
}