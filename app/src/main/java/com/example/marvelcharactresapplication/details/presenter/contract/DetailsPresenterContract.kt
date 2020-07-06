package com.example.marvelcharactresapplication.details.presenter.contract

import android.content.Intent
import com.example.marvelcharactresapplication.dto.CharactersDTO

interface DetailsPresenterContract {
    fun createMenu()
    fun setIntent(intent: Intent)
    fun initComponents()
    fun noFavorite()
    fun favorite(characters: CharactersDTO)
}