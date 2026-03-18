package com.example.quotesapp2026.api

import com.example.quotesapp2026.model.QuoteListModelItem

sealed class Response<T>(val data: T? = null, val errorMessage: String? = null)
{
    class Loading<T> : Response<T>()
    class Success<T>(data: T? = null) : Response<T>(data = data)
    class Error<T>(errorMessage: String) : Response<T>(errorMessage = errorMessage)
}