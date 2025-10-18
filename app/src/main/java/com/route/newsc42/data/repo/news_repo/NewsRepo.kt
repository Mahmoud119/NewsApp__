package com.route.newsc42.data.repo.news_repo

import com.route.newsc42.data.api.model.SourceDM
import com.route.newsc42.data.utils.ApiResult

interface NewsRepo {
    suspend  fun loadSources( categoryId: String): ApiResult<List<SourceDM>>
}