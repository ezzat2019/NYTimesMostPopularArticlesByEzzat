package com.example.nytimesmostpopulararticlesbyezzat.data.api

import com.example.nytimesmostpopulararticlesbyezzat.data.models.ResultArcticleModel

interface ArticleApiImp {
    suspend fun getArticle(key: String): ResultArcticleModel
}