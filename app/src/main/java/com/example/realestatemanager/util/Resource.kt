package com.example.realestatemanager.util

sealed class Resource<T>(
    val data: T? = null,
    val message: String? = null
) {

    class Success<T>(data: T?) : Resource<T>(data)
    class Error<T>(mesage: String, data: T? = null) : Resource<T>(data, mesage)
    class Loading<T>(data: T? = null) : Resource<T>(null)

}