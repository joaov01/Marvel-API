package com.example.marvelcharactresapplication.favorite.presenter.contract

import com.example.marvelcharactresapplication.model.Data

interface FavoritePresenterContract {

    fun setFavorite(characters: Data)
    fun resultOk()
    fun setNoItens()
}