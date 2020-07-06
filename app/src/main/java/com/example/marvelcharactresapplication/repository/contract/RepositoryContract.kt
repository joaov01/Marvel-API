package com.example.marvelcharactresapplication.repository.contract

import com.example.marvelcharactresapplication.model.Characters

interface RepositoryContract {

    fun getCharacters(success: ServiceCallback<Characters>, error: ServiceCallback<String>)
}