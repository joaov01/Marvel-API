package com.example.marvelcharactresapplication.details.useCase.contract

import android.content.Intent
import com.example.marvelcharactresapplication.dto.CharactersDTO

interface DetailsUseCaseContract {
    fun createMenu()
    fun verifyIntent(intent: Intent)
    fun initComponents()
    fun verifyFavorites(characters: CharactersDTO?)

}