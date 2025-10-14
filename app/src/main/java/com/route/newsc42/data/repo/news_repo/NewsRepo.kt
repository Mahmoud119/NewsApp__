package com.route.newsc42.data.repo.news_repo

import android.icu.lang.UCharacter.GraphemeClusterBreak.L
import com.route.newsc42.data.api.model.SourceDM
import com.route.newsc42.data.repo.news_repo.data_sources.local_data_source.NewsLocalDataSource
import com.route.newsc42.data.repo.news_repo.data_sources.remote_data_source.NewsRemoteDataSource
import com.route.newsc42.data.utils.ApiResult
import com.route.newsc42.ui.utils.Resource

class NewsRepo(val RemoteDateSource :NewsRemoteDataSource,
               val LocalDataSource :NewsLocalDataSource) {


  suspend  fun loadSources(): ApiResult<List<SourceDM>>{
       try{
           val isConnected =true
           return  if (isConnected){

               return  when( val result = RemoteDateSource.loadSources()){
                   is ApiResult.Error -> {
                       result
                   }
                   is ApiResult.Success -> {
                       LocalDataSource.saveSources(result.data)
                       result
                   }
               }
               // return newsRemoteDateSource.loadSources()
           }else{
               LocalDataSource.loadSources()
           }
       }catch (t:Throwable){
            return ApiResult.Error(t.localizedMessage)
       }



    }
}