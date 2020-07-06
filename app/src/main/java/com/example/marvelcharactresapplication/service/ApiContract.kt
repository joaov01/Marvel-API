package com.example.marvelcharactresapplication.service

import com.example.marvelcharactresapplication.model.Characters
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiContract {

    @GET("/v1/public/characters")
    fun getCharacters(@Query("ts") ts: String,
        @Query("apikey") apiKey: String,
        @Query("hash") hash : String,
        @Query("limit")limit: String): Call<Characters>

}