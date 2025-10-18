package com.route.newsc42.data.repo.news_repo.data_sources.local_data_source

import com.route.newsc42.data.api.model.SourceDM
import com.route.newsc42.data.utils.ApiResult

interface NewsLocalDataSource {
    suspend fun loadSources(): ApiResult<List<SourceDM>>
    suspend fun saveSources(sources: List<SourceDM>)
}


