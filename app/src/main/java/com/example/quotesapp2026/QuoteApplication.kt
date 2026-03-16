package com.example.quotesapp2026

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequest
import androidx.work.WorkManager
import com.example.quotesapp2026.api.QuoteService
import com.example.quotesapp2026.api.RetrofitHelper
import com.example.quotesapp2026.database.QuoteDatabase
import com.example.quotesapp2026.repository.QuotesRepository
import com.example.quotesapp2026.worker.QuoteWorker
import kotlin.jvm.java

class QuoteApplication : Application() {

    lateinit var quotesRepository: QuotesRepository

    override fun onCreate() {
        super.onCreate()
        initialize()
    }

    private fun initialize() {

        val quoteService = RetrofitHelper.getInstance().create(QuoteService::class.java)
        val quoteDatabase = QuoteDatabase.getDatabase(applicationContext)
        quotesRepository = QuotesRepository(quoteService,quoteDatabase,applicationContext)

        createNotificationChannel(applicationContext)

    }

    fun createNotificationChannel(context: Context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                "quote_channel",
                "Quotes",
                NotificationManager.IMPORTANCE_HIGH
            ).apply {
                description = "Channel for quote notifications"
            }

            val manager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            manager.createNotificationChannel(channel)
        }
    }
}