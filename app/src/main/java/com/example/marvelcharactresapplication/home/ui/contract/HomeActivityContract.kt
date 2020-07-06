package com.example.marvelcharactresapplication.home.ui.contract

import com.example.marvelcharactresapplication.model.Characters
import com.example.marvelcharactresapplication.model.Data

interface HomeActivityContract {

    fun setError(error: String)
    fun setCharacters(characters: Characters)
    fun setSearchNoResult()
    fun setSearch(data: Data)
    fun initComponents()
    fun reloadCharacters()
}