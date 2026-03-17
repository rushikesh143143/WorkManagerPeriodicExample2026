package com.example.quotesapp2026

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.quotesapp2026.adapter.QuotesAdapt
import com.example.quotesapp2026.databinding.ActivityMainBinding
import com.example.quotesapp2026.viewmodels.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.DelicateCoroutinesApi

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val mainViewModel: MainViewModel by viewModels()
    lateinit var qadapter : QuotesAdapt

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


        binding.shimmerLayout.startShimmer()
        binding.shimmerLayout.visibility = View.VISIBLE
        qadapter = QuotesAdapt()



        binding.quotesRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.quotesRecyclerView.setHasFixedSize(true)
        binding.quotesRecyclerView.adapter = qadapter


        mainViewModel.quotes.observe(this, Observer{
            qadapter.submitList(it)
            binding.shimmerLayout.startShimmer()
            binding.shimmerLayout.visibility = View.GONE
        })

       mainViewModel.startQuotesNotificationBackground()

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


}