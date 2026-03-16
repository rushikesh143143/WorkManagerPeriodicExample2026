package com.example.quotesapp2026.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.quotesapp2026.model.QuoteListModel
import com.example.quotesapp2026.repository.QuotesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel (val quotesRepository: QuotesRepository) : ViewModel()
{

    init {

        viewModelScope.launch(Dispatchers.IO) {
        quotesRepository.getQuotes()
        }


    }

    //point to repo live data
    val quotes : LiveData<QuoteListModel>
    get() = quotesRepository.quotes

    fun startQuotesNotificationBackground()
    {
        quotesRepository.runQuoteBackgroundWork()
    }

}
