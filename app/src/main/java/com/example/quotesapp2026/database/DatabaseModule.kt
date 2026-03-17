package com.example.quotesapp2026.database

import android.app.Application
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(application: Application): QuoteDatabase {
        return Room.databaseBuilder(
            application,
            QuoteDatabase::class.java,
            "quote_db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideQuoteDao(db: QuoteDatabase): QuoteDAO {
        return db.quoteDao()
    }
}