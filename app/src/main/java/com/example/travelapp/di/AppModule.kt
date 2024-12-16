package com.example.travelapp.di

import com.example.travelapp.network.TravelApi
import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val appModule = module {

    single {
        HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }

    single {
        OkHttpClient.Builder()
            .addInterceptor(get<HttpLoggingInterceptor>())
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .build()
    }

    single {
        Gson()
    }

    single {
        Retrofit.Builder()
            .baseUrl("https://raw.githubusercontent.com/") // add base url
            .client(get())
            .addConverterFactory(GsonConverterFactory.create(get()))
            .build()
            .create(TravelApi::class.java)
    }
}