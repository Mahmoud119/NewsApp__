package com.route.newsc42.ui.screens.news_fragment

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.route.newsc42.api.ApiManager
import com.route.newsc42.api.model.ArticleDM
import com.route.newsc42.api.model.SourceDM
import com.route.newsc42.ui.utils.Resource
import kotlinx.coroutines.launch


class NewsViewModel : ViewModel() {

    var sourcesState = MutableLiveData<Resource<List<SourceDM>>>()
    var articlesState = MutableLiveData<Resource<List<ArticleDM>>>()

    fun loadSources(categoryId: String) {

        viewModelScope.launch {

            sourcesState.value = Resource.Loading()
            try {
                val response = ApiManager.getWebServices().loadSources(categoryId)
                sourcesState.value = Resource.Success(response.sources ?: listOf())
                //sources.value = response.sources ?: listOf()
                //isLoading.value = false
            } catch (e: Throwable) {
                sourcesState.value = Resource.Error(e.localizedMessage)

            }
        }

    }

    fun loadArticles(sourceId: String) {
            articlesState.value = Resource.Loading()
        viewModelScope.launch {
            try {
                val response = ApiManager.getWebServices().loadArticles(sourceId)
                articlesState.value = Resource.Success(response.articles ?: listOf())
            } catch (t: Throwable) {
                articlesState.value= Resource.Error(t.localizedMessage)
            }

        }
    }

}





