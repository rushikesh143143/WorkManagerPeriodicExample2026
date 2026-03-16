package com.example.quotesapp2026.api

import com.example.quotesapp2026.model.QuoteListModel
import retrofit2.Response
import retrofit2.http.GET

interface QuoteService {

    @GET("quotes")
    suspend fun getQuotes() : Response<QuoteListModel>

    @GET("random")
    suspend fun getRandomQuotes() : Response<QuoteListModel>



}