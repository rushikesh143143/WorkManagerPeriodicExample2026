package com.example.quotesapp2026.worker

import android.app.NotificationManager
import android.content.Context
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.quotesapp2026.R
import com.example.quotesapp2026.database.QuoteDatabase
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class QuoteWorker(context: Context,params: WorkerParameters) : CoroutineWorker(context,params) {
    override suspend fun doWork(): Result {

        val quoteDatabase = QuoteDatabase.getDatabase(applicationContext)
        val quotes = quoteDatabase.quoteDao().getRandomQuote()

        showQuoteNotification(applicationContext,quotes.q)

        return Result.success()
    }



    fun showQuoteNotification(context: Context, quote: String) {
        val channelId = "quote_channel"

        // Get current time
        val time = SimpleDateFormat("hh:mm a", Locale.getDefault()).format(Date())

        // Create notification
        val notification = NotificationCompat.Builder(context, channelId)
            .setSmallIcon(R.drawable.ic_launcher_background) // your notification icon
            .setContentTitle("Quote of the Moment")
            .setContentText("$quote • $time")
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setAutoCancel(true)
            .build()

        val manager = ContextCompat.getSystemService(context, NotificationManager::class.java)
        manager?.notify(System.currentTimeMillis().toInt(), notification)
    }





}
