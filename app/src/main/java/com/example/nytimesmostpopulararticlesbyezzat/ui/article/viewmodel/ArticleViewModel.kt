package com.example.nytimesmostpopulararticlesbyezzat.ui.article.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nytimesmostpopulararticlesbyezzat.data.models.ResultArcticleModel
import com.example.nytimesmostpopulararticlesbyezzat.data.repo.ArticleRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class ArticleViewModel @ViewModelInject constructor(val repo: ArticleRepo) : ViewModel() {
    val liveDataArticle: MutableLiveData<ResultArcticleModel> = MutableLiveData()


    fun getArticle(api_key: String): MutableLiveData<ResultArcticleModel> {
        viewModelScope.launch(Dispatchers.Main) {
            async {
                liveDataArticle.value = repo.articleApi.getArticle(api_key)
            }.await()

        }
        return liveDataArticle

    }


}