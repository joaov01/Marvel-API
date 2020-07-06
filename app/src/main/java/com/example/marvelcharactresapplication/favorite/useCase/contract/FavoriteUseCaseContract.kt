package com.example.marvelcharactresapplication.favorite.useCase.contract

interface FavoriteUseCaseContract {
    fun getFavorites()
    fun checkResultOk(isOk : Boolean)
}