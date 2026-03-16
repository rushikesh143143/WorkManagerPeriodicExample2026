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
import com.example.quotesapp2026.databinding.ActivityMainBinding
import com.example.quotesapp2026.viewmodels.MainViewModel
import com.example.quotesapp2026.viewmodels.MainViewModelFactory
import kotlinx.coroutines.DelicateCoroutinesApi

class MainActivity : AppCompatActivity() {

    lateinit var mainViewModel: MainViewModel
    lateinit var adapter: QuotesAdapter

    //view binding object step 1
    private var _binding : ActivityMainBinding ? = null
    private  val binding get() = _binding!!



    @OptIn(DelicateCoroutinesApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //viewbinding step 2
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            requestPermissions(arrayOf(android.Manifest.permission.POST_NOTIFICATIONS), 101)
        }


        binding.progressBar.visibility = View.VISIBLE

        adapter = QuotesAdapter()
        binding.quotesRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.quotesRecyclerView.adapter = adapter


       val quotesRepository = (application as QuoteApplication).quotesRepository

       mainViewModel = ViewModelProvider(this, MainViewModelFactory(quotesRepository))[MainViewModel::class.java]

       mainViewModel.quotes.observe(this, Observer{
           Log.d("Quotes", it.toString())
           adapter.setQuotes(it)
           binding.progressBar.visibility = View.GONE
       })

       mainViewModel.startQuotesNotificationBackground()


    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


}