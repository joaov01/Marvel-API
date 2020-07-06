package com.example.marvelcharactresapplication.favorite.ui.contract

import com.example.marvelcharactresapplication.model.Data

interface FavoriteActivityContract {

    fun setFavorite(characters: Data)
    fun resultOk()
    fun layoutNoItens()
}