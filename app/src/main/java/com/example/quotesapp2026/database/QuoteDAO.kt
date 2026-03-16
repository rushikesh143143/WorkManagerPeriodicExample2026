package com.example.quotesapp2026.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
@Dao
interface QuoteDAO {

    @Insert
    suspend fun addQuotes(quotes : List<QuotesTable>)

    @Query("SELECT * FROM quotes")
    suspend fun getQuotes() : List<QuotesTable>

    @Query("SELECT * FROM quotes ORDER BY RANDOM() LIMIT 1")
    suspend fun getRandomQuote() : QuotesTable


}
