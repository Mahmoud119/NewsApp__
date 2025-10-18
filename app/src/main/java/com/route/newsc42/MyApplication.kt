package com.route.newsc42

import android.app.Application
import com.route.newsc42.data.database.MyDatabase
import com.route.newsc42.data.utils.Connectivity

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        MyDatabase.init(this)
        Connectivity.context = this

    }
}