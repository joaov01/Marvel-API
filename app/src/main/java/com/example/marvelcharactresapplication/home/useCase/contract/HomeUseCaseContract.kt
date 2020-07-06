package com.example.marvelcharactresapplication.home.useCase.contract

import com.example.marvelcharactresapplication.model.Data

interface HomeUseCaseContract{
    fun getCharacters()
    fun initComponents()
    fun search(characters: Data, s: String)
    fun verifyResult(isOk: Boolean)
}