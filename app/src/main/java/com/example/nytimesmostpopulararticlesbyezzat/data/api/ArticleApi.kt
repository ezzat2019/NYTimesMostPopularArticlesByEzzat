package com.example.nytimesmostpopulararticlesbyezzat.data.api

import com.example.nytimesmostpopulararticlesbyezzat.data.models.ResultArcticleModel
import retrofit2.http.GET
import retrofit2.http.Query

interface ArticleApi {

    @GET("svc/mostpopular/v2/mostviewed/all-sections/7.json")
    suspend fun getArticle(@Query(value = "api-key") api_key: String): ResultArcticleModel
}