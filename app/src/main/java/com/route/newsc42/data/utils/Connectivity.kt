package com.route.newsc42.data.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import androidx.core.content.ContextCompat.getSystemService

class Connectivity {
    companion object
    {
        lateinit var context: Context
    }

    fun isConnected(): Boolean {
        val connectivityManager = getSystemService(context,ConnectivityManager::class.java)
        val nw = connectivityManager.activeNetwork ?: return false
        val activeNetworkInfo = connectivityManager.getNetworkCapabilities(nw) ?: return false
        return when{
            activeNetworkInfo.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
            activeNetworkInfo.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
            else -> false

        }
    }
}

