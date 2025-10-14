package com.route.newsc42.data.repo.news_repo.data_sources.remote_data_source

import com.route.newsc42.data.api.WebServices
import com.route.newsc42.data.api.model.SourceDM
import com.route.newsc42.data.utils.ApiResult

class NewsRemoteDataSource (val webServices: WebServices){
    suspend fun loadSources(categoryId: String): ApiResult<List<SourceDM>>{
        try {
            val response = webServices.loadSources(categoryId)
            return ApiResult.Success( response.sources ?: emptyList())
        }catch (t:Throwable){
            return ApiResult.Error(t.localizedMessage)
        }

    }
}