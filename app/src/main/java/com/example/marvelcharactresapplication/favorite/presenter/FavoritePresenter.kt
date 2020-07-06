package com.example.marvelcharactresapplication.favorite.presenter

import com.example.marvelcharactresapplication.favorite.presenter.contract.FavoritePresenterContract
import com.example.marvelcharactresapplication.favorite.ui.contract.FavoriteActivityContract
import com.example.marvelcharactresapplication.model.Data

class FavoritePresenter(private val view: FavoriteActivityContract): FavoritePresenterContract {

    override fun setFavorite(characters: Data) {
        view.setFavorite(characters)
    }

    override fun resultOk() {
        view.resultOk()
    }

    override fun setNoItens() {
        view.layoutNoItens()
    }
}