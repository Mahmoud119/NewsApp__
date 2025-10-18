package com.route.newsc42.data.repo.news_repo

import com.route.newsc42.data.api.model.SourceDM
import com.route.newsc42.data.repo.news_repo.data_sources.local_data_source.NewsLocalDataSource
import com.route.newsc42.data.repo.news_repo.data_sources.local_data_source.NewsLocalDataSourceImpl
import com.route.newsc42.data.repo.news_repo.data_sources.remote_data_source.NewsRemoteDataSource
import com.route.newsc42.data.repo.news_repo.data_sources.remote_data_source.NewsRemoteDataSourceImpl
import com.route.newsc42.data.utils.ApiResult
import com.route.newsc42.data.utils.Connectivity

class NewsRepoImpl(val RemoteDateSource : NewsRemoteDataSource,
                   val LocalDataSource : NewsLocalDataSource,
                   val connectivity: Connectivity
    ) : NewsRepo {


  override suspend  fun loadSources(categoryId: String): ApiResult<List<SourceDM>>{
       try{

           return  if (connectivity.isConnected()){

               return  when( val result = RemoteDateSource.loadSources(categoryId)){
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