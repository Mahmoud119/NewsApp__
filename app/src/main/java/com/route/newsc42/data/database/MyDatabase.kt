package com.route.newsc42.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

import com.route.newsc42.data.api.model.SourceDM
import com.route.newsc42.data.database.daos.SourcesDao

@Database(entities = [SourceDM::class], version = 1)
abstract class MyDatabase : RoomDatabase() {

   abstract fun getSourcesDao(): SourcesDao

   companion object{

      private var database : MyDatabase? = null

      fun init(context : Context){
         if (database == null){
            database = Room.databaseBuilder(context,MyDatabase::class.java,"todo_database")
               .fallbackToDestructiveMigration()
               .build()
         }
      }
      fun getInstance() : MyDatabase {

         return database!!
      }
   }


}


