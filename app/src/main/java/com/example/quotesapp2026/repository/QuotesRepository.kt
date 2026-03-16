package com.example.quotesapp2026.repository

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequest
import androidx.work.WorkManager
import com.example.quotesapp2026.api.QuoteService
import com.example.quotesapp2026.database.QuoteDatabase
import com.example.quotesapp2026.mapper.toQuoteListModelItem
import com.example.quotesapp2026.mapper.toQuotesTable
import com.example.quotesapp2026.model.QuoteListModel
import com.example.quotesapp2026.model.QuoteListModelItem
import com.example.quotesapp2026.utils.NetworkUtils
import com.example.quotesapp2026.worker.QuoteWorker

class QuotesRepository(
    private val quoteService: QuoteService,
    private val quoteDatabase: QuoteDatabase,
    private val applicationContext: Context
) {

    private val quotesLiveData = MutableLiveData<QuoteListModel>()

    private val workManager = WorkManager.getInstance(applicationContext)



    val quotes : LiveData<QuoteListModel>
    get() = quotesLiveData


    suspend fun getQuotes()
    {
        //check internet connection
        if(NetworkUtils.isInternetAvailabe(applicationContext))
        {
            val result = quoteService.getQuotes()

            if(result.body() != null)
            {

                val quotes = result.body()!!.map {
                    it.toQuotesTable()
                }

                quoteDatabase.quoteDao().addQuotes(quotes)
                quotesLiveData.postValue(result.body())
            }
        }
        else
        {
            val quotes = quoteDatabase.quoteDao().getQuotes()

            val mappedQuotes = quotes.map {
                it.toQuoteListModelItem()
            }

            val quoteListModel = QuoteListModel().apply {
                addAll(mappedQuotes)
            }

            quotesLiveData.postValue(quoteListModel)

            
        }
        
        
        
    }


    fun runQuoteBackgroundWork()
    {

        val request = PeriodicWorkRequest
            .Builder(QuoteWorker::class.java,30, java.util.concurrent.TimeUnit.MINUTES)
            .setInitialDelay(15, java.util.concurrent.TimeUnit.SECONDS)
            .build()

        workManager.enqueueUniquePeriodicWork("quote_worker", ExistingPeriodicWorkPolicy.KEEP, request)
    }


    }




