package com.example.nytimesmostpopulararticlesbyezzat.utills.Modules

import com.example.nytimesmostpopulararticlesbyezzat.data.api.ArticleApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(ApplicationComponent::class)
class HiltModules {
    @Provides
    @Singleton
    fun provideRetrofit() = Retrofit.Builder().baseUrl("https://api.nytimes.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Provides
    @Singleton
    fun provideArticleApi(retofit: Retrofit) = retofit.create(ArticleApi::class.java)

}