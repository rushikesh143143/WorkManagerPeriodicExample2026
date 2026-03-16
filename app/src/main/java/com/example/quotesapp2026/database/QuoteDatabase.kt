package com.example.quotesapp2026.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.quotesapp2026.database.QuoteDatabase.Companion.INSTANCE

@Database(entities = [QuotesTable::class], version = 1)
abstract class QuoteDatabase : RoomDatabase() {

    abstract fun quoteDao(): QuoteDAO

    companion object{

        @Volatile
        private var INSTANCE : QuoteDatabase? = null

        fun getDatabase(context: Context) : QuoteDatabase {

         if(INSTANCE == null)
         {

             synchronized(this){

                 INSTANCE = Room.databaseBuilder(
                     context,
                     QuoteDatabase::class.java,
                     "quote_database")
                     .build()
             }
         }

            return INSTANCE!!
        }

    }


}