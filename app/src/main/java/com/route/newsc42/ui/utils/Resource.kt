package com.route.newsc42.ui.utils

sealed class Resource<T>{
    class Success<E>(val data : E): Resource<E>(){}
    class Error<T>(val errorMessage: String): Resource<T>(){}
    class Loading<T>: Resource<T>(){}
    class Initial<T>: Resource<T>(){}

}