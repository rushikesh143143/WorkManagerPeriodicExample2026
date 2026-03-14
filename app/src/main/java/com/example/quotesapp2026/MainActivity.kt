package com.example.quotesapp2026

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.icu.util.TimeUnit
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import androidx.work.Constraints
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.ExistingWorkPolicy
import androidx.work.OneTimeWorkRequest
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.PeriodicWorkRequest
import androidx.work.WorkManager
import com.example.quotesapp2026.viewmodels.MainViewModel
import com.example.quotesapp2026.database.UserDb
import com.example.quotesapp2026.database.UserTable
import com.example.quotesapp2026.viewmodels.MainViewModelFactory
import com.example.quotesapp2026.worker.QuoteWorker
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    //lateinit var userdb : UserDb
    lateinit var mainViewModel: MainViewModel

    lateinit var workManager: WorkManager


    @OptIn(DelicateCoroutinesApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        workManager = WorkManager.getInstance(applicationContext)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            requestPermissions(arrayOf(android.Manifest.permission.POST_NOTIFICATIONS), 101)
        }

        ///userdb = Room.databaseBuilder(applicationContext, UserDb::class.java, "UserDb").build()
        mainViewModel = ViewModelProvider(this, MainViewModelFactory("Hello"))[MainViewModel::class.java]

        GlobalScope.launch {
           // userdb.userdao().insert(UserTable(1, "Rahul", "rahul@gmial.com", "123456"))
        }

        createNotificationChannel(this)


        val request = PeriodicWorkRequest
            .Builder(QuoteWorker::class.java,16, java.util.concurrent.TimeUnit.MINUTES)
            .setInitialDelay(15, java.util.concurrent.TimeUnit.SECONDS)
            .build()

        workManager.enqueueUniquePeriodicWork("quote_worker", ExistingPeriodicWorkPolicy.KEEP, request)


      /*  val request = OneTimeWorkRequest.Builder(QuoteWorker::class.java)
            .setInitialDelay(15, java.util.concurrent.TimeUnit.SECONDS)
            .build()*/

      // workManager.enqueueUniqueWork("quote_worker", ExistingWorkPolicy.KEEP, request)

       workManager.getWorkInfoByIdLiveData(request.id).observe(this)
       {
           if(it != null)
           {
               Log.e("Work", "onCreate: "+it.state.name )
           }
       }


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