package com.example.realestatemanager.feature.details.model

sealed class ResponseState<out T> {

    object Idle : ResponseState<Nothing>()

    object Loading : ResponseState<Nothing>()

    data class Error(val error: Throwable) : ResponseState<Nothing>()

    data class Success<out T>(val data: T) : ResponseState<T>()

}
