package com.example.quotesapp2026.api

import com.example.quotesapp2026.BuildConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RetrofitHelperDI {


    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit
    {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }


    @Singleton
    @Provides
    fun provideQuoteService(retrofit: Retrofit) : QuoteService
    {
        return retrofit.create(QuoteService::class.java)
    }

}