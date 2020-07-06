package com.example.marvelcharactresapplication.home.presenter.contract

import com.example.marvelcharactresapplication.model.Characters
import com.example.marvelcharactresapplication.model.Data

interface HomePresenterContract {

    fun setCharacters(characters: Characters)
    fun setError(error: String)
    fun setSearch(data: Data)
    fun initComponents()
    fun resultIsOk()
}