package com.route.newsc42.ui.screens.news_fragment

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.route.newsc42.data.api.ApiManager
import com.route.newsc42.data.api.model.ArticleDM
import com.route.newsc42.data.api.model.SourceDM
import com.route.newsc42.data.database.MyDatabase
import com.route.newsc42.data.repo.news_repo.NewsRepoImpl
import com.route.newsc42.data.repo.news_repo.data_sources.local_data_source.NewsLocalDataSourceImpl
import com.route.newsc42.data.repo.news_repo.data_sources.remote_data_source.NewsRemoteDataSourceImpl
import com.route.newsc42.data.utils.ApiResult
import com.route.newsc42.ui.utils.Resource
import kotlinx.coroutines.launch


class NewsViewModel : ViewModel() {
    val reposoitory = NewsRepoImpl(NewsRemoteDataSourceImpl(ApiManager.getWebServices()) , NewsLocalDataSourceImpl(
        MyDatabase.getInstance().getSourcesDao()))

    var sourcesState = MutableLiveData<Resource<List<SourceDM>>>()
    var articlesState = MutableLiveData<Resource<List<ArticleDM>>>()

    fun loadSources(categoryId: String) {

        viewModelScope.launch {

            sourcesState.value = Resource.Loading()
            try {
                when(val response = reposoitory.loadSources(categoryId)){
                    is ApiResult.Error -> sourcesState.value = Resource.Error(response.errorMessage)
                    is ApiResult.Success -> sourcesState.value = Resource.Success(response.data)
                }


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





