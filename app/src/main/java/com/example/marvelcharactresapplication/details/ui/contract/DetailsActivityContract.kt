package com.example.marvelcharactresapplication.details.ui.contract

import android.content.Intent
import com.example.marvelcharactresapplication.dto.CharactersDTO

interface DetailsActivityContract {

    fun createMenu()
    fun setIntent(intent: Intent)
    fun initComponents()
    fun noFavorite()
    fun favorite(characters: CharactersDTO)
}