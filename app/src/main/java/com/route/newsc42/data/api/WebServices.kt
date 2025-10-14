package com.route.newsc42.data.api

import com.route.newsc42.data.api.model.ArticlesResponse
import com.route.newsc42.data.api.model.SourcesResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface WebServices {

    @GET("/v2/top-headlines/sources")
    suspend fun loadSources(
        @Query("category") categoryId: String
    ): SourcesResponse

    @GET("/v2/everything")
    suspend fun loadArticles(
        @Query("sources") sourceId: String
    ): ArticlesResponse

}