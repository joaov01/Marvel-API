package com.example.marvelcharactresapplication.home.presenter

import com.example.marvelcharactresapplication.home.presenter.contract.HomePresenterContract
import com.example.marvelcharactresapplication.home.ui.contract.HomeActivityContract
import com.example.marvelcharactresapplication.model.Characters
import com.example.marvelcharactresapplication.model.Data

class HomePresenter(private val view: HomeActivityContract): HomePresenterContract{

    override fun setCharacters(characters: Characters) {
        view.setCharacters(characters)
    }

    override fun setError(error: String) {
        view.setError(error)
    }

    override fun setSearch(data: Data) {
        if(data.results.isNotEmpty()){
            view.setSearch(data)
        }else{
            view.setSearchNoResult()
        }
    }

    override fun initComponents() {
        view.initComponents()
    }

    override fun resultIsOk() {
        view.reloadCharacters()
    }
}