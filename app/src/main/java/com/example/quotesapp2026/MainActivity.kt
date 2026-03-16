package com.example.quotesapp2026

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.quotesapp2026.adapter.QuotesAdapter
import com.example.quotesapp2026.viewmodels.MainViewModel
import com.example.quotesapp2026.viewmodels.MainViewModelFactory
import kotlinx.coroutines.DelicateCoroutinesApi

class MainActivity : AppCompatActivity() {

    lateinit var mainViewModel: MainViewModel
    lateinit var adapter: QuotesAdapter

    @OptIn(DelicateCoroutinesApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            requestPermissions(arrayOf(android.Manifest.permission.POST_NOTIFICATIONS), 101)
        }

        val progressBar = findViewById<ProgressBar>(R.id.progressBar)
        val recyclerView = findViewById<RecyclerView>(R.id.quotesRecyclerView)


        progressBar.visibility = View.VISIBLE
        adapter = QuotesAdapter()
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter


       val quotesRepository = (application as QuoteApplication).quotesRepository

       mainViewModel = ViewModelProvider(this, MainViewModelFactory(quotesRepository))[MainViewModel::class.java]

       mainViewModel.quotes.observe(this, Observer{
           Log.d("Quotes", it.toString())
           adapter.setQuotes(it)
           progressBar.visibility = View.GONE
       })

       mainViewModel.startQuotesNotificationBackground()


    }


}