package com.route.newsc42.data.utils

sealed class ApiResult<T>{
    class Success<E>(val data : E): ApiResult<E>(){}
    class Error<T>(val errorMessage: String): ApiResult<T>(){}

}