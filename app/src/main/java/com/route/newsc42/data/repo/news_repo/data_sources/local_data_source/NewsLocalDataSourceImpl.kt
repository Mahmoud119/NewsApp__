package com.route.newsc42.data.repo.news_repo.data_sources.local_data_source

import com.route.newsc42.data.api.model.SourceDM
import com.route.newsc42.data.database.daos.SourcesDao
import com.route.newsc42.data.utils.ApiResult

class NewsLocalDataSourceImpl (val sourcesDao : SourcesDao):NewsLocalDataSource {
     override suspend fun loadSources(): ApiResult<List<SourceDM>>{
        try {
            return ApiResult.Success(sourcesDao.loadSources())
        }catch (t : Throwable){
            return ApiResult.Error(t.localizedMessage)
        }

    }
    override suspend fun saveSources(sources: List<SourceDM>){

        sourcesDao.saveSources(sources)
    }
}