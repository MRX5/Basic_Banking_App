package com.example.bankingapp.domain.utils

sealed class DataState<out R>{
    data class Success<out T>(val data:T):DataState<T>()
    data class Error(val exception:String):DataState<Nothing>()
    object Loading:DataState<Nothing>()
    object Idle:DataState<Nothing>()

}