package com.example.marvelcharactresapplication.details.presenter

import android.content.Intent
import com.example.marvelcharactresapplication.details.presenter.contract.DetailsPresenterContract
import com.example.marvelcharactresapplication.details.ui.contract.DetailsActivityContract
import com.example.marvelcharactresapplication.dto.CharactersDTO

class DetailsPresenter(private val view: DetailsActivityContract): DetailsPresenterContract {
    override fun createMenu() {
        view.createMenu()
    }

    override fun setIntent(intent: Intent) {
        view.setIntent(intent)
    }

    override fun initComponents() {
        view.initComponents()
    }

    override fun noFavorite() {
        view.noFavorite()
    }

    override fun favorite(characters: CharactersDTO) {
        view.favorite(characters)
    }
}