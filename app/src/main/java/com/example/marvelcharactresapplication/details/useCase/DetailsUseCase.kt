package com.example.marvelcharactresapplication.details.useCase

import android.content.Intent
import com.example.marvelcharactresapplication.details.presenter.contract.DetailsPresenterContract
import com.example.marvelcharactresapplication.details.useCase.contract.DetailsUseCaseContract
import com.example.marvelcharactresapplication.dto.CharactersDTO
import com.example.marvelcharactresapplication.general.CHARACTERS

class DetailsUseCase(private val presenter: DetailsPresenterContract) : DetailsUseCaseContract {
    override fun createMenu() {
        presenter.createMenu()
    }

    override fun verifyIntent(intent: Intent) {
        if(intent.hasExtra(CHARACTERS)){
            presenter.setIntent(intent)
        }
    }

    override fun initComponents() {
        presenter.initComponents()
    }

    override fun verifyFavorites(characters: CharactersDTO?) {
        if(characters != null){
            presenter.favorite(characters)
        }else{
            presenter.noFavorite()
        }
    }
}