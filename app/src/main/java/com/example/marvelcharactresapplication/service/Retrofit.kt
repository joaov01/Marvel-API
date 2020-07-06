package com.example.marvelcharactresapplication.service

import com.example.marvelcharactresapplication.general.URL_BASE
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Retrofit {

    val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(URL_BASE)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}