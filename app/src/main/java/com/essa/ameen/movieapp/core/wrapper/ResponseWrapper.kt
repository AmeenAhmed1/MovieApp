package com.essa.ameen.movieapp.core.wrapper

sealed class ResponseWrapper<out T> {
    data class Success<out T>(val value: T) : ResponseWrapper<T>()
    data class Fail(val error: String) : ResponseWrapper<Nothing>()
}
