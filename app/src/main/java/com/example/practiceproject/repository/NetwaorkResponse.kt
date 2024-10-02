package com.example.practiceproject.repository

sealed class NetworkResponse<out T> {
    data class Success<out T> (val membersList : T) : NetworkResponse<T>()
    data class Error(val message : String) : NetworkResponse<Nothing>()
    object Loading : NetworkResponse<Nothing>()
}