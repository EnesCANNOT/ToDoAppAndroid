package com.helloworldstudios.todoappandroid.util

sealed class Resource<T>(val data: T? = null, val message: String? = null, val httpCode: String? = null) {
    class Success<T>(data: T, message: String? = "Successful.", httpCode: String? = null): Resource<T>(data, message, httpCode)
    class Error<T>(data: T? = null, message: String, httpCode: String?): Resource<T>(data, message, httpCode)
    class Loading<T>(data: T? = null, message: String?, httpCode: String?): Resource<T>(data, message, httpCode)
}