package com.example.quotesapp2026

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.quotesapp2026.viewmodels.MainViewModel
import com.example.quotesapp2026.viewmodels.MainViewModelFactory
import kotlinx.coroutines.DelicateCoroutinesApi

class MainActivity : AppCompatActivity() {

    lateinit var mainViewModel: MainViewModel

    @OptIn(DelicateCoroutinesApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            requestPermissions(arrayOf(android.Manifest.permission.POST_NOTIFICATIONS), 101)
        }

       val quotesRepository = (application as QuoteApplication).quotesRepository

       mainViewModel = ViewModelProvider(this, MainViewModelFactory(quotesRepository))[MainViewModel::class.java]

       mainViewModel.quotes.observe(this, Observer{
           Log.d("Quotes", it.toString())
           Toast.makeText(this, it.size.toString(), Toast.LENGTH_SHORT).show()
       })

       mainViewModel.startQuotesNotificationBackground()

    }


}