package com.route.newsc42.data.database.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.Companion.REPLACE
import androidx.room.Query
import com.route.newsc42.data.api.model.SourceDM
import retrofit2.http.GET

@Dao
interface SourcesDao {
    @Insert(onConflict = REPLACE)
    suspend fun saveSources(sources : List<SourceDM>)
    @Query("select * from SourceDM" )
    suspend fun  loadSources(): List<SourceDM>
}