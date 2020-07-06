package com.example.marvelcharactresapplication.repository

import com.example.marvelcharactresapplication.general.*
import com.example.marvelcharactresapplication.model.Characters
import com.example.marvelcharactresapplication.repository.contract.RepositoryContract
import com.example.marvelcharactresapplication.repository.contract.ServiceCallback
import com.example.marvelcharactresapplication.service.ApiContract
import com.example.marvelcharactresapplication.service.Retrofit
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Repository: RepositoryContract {

    private val service = Retrofit().retrofit.create(ApiContract::class.java)

    override fun getCharacters(
        sucess: ServiceCallback<Characters>,
        error: ServiceCallback<String>
    ) {
        val characters = service.getCharacters(TS, API_KEY, TOKEN.toMd5(), LIMIT)
        characters.enqueue(object: Callback<Characters> {

            override fun onFailure(call: Call<Characters>, t: Throwable) {
                if(t.message.toString().toLowerCase().contains(NO_INTERNET)){
                    error.callback(MSG_INTERNET)
                }else{
                    error.callback(t.message.toString())
                }
            }

            override fun onResponse(call: Call<Characters>, response: Response<Characters>) {
                if(response.code() == 200 && response.body() != null){
                    val characters = response.body()
                    sucess.callback(characters!!)
                }
            }
        })
    }
}