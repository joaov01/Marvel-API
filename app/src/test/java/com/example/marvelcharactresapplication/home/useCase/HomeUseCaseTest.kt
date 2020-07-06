package com.example.marvelcharactresapplication.home.useCase

import com.example.marvelcharactresapplication.general.NUMBER_ONE
import com.example.marvelcharactresapplication.home.presenter.contract.HomePresenterContract
import com.example.marvelcharactresapplication.model.Characters
import com.example.marvelcharactresapplication.model.Data
import com.example.marvelcharactresapplication.model.Result
import com.example.marvelcharactresapplication.model.Thumbnail
import com.example.marvelcharactresapplication.repository.Repository
import com.example.marvelcharactresapplication.repository.contract.ServiceCallback
import com.google.gson.Gson
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.argumentCaptor
import com.nhaarman.mockitokotlin2.atLeastOnce
import com.nhaarman.mockitokotlin2.verify
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.mockito.Mockito

class HomeUseCaseTest {

    private val presenter = Mockito.mock(HomePresenterContract::class.java)
    private val repository = Mockito.mock(Repository::class.java)
    private val useCase = HomeUseCase(presenter, repository)
    private lateinit var th: Thumbnail
    private lateinit var result: Result
    private val results = mutableListOf<Result>()
    private lateinit var data: Data

    @Before
    fun setUp(){
        th = Thumbnail("", "")
        result = Result(NUMBER_ONE, "", "", th)
        results.add(result)
        data = Data(results)
    }

    @Test
    fun getCharacters() {
        argumentCaptor<ServiceCallback<Characters>> {
            useCase.getCharacters()
            verify(repository).getCharacters(this.capture(), any())
            val characters = Characters(data)
            this.firstValue.callback(characters)
            assertEquals(1, characters.data.results.size)
        }
    }

    @Test
    fun initComponents() {
        useCase.initComponents()
        verify(presenter, atLeastOnce()).initComponents()
    }

    @Test
    fun search() {
        useCase.search(data, "")
        verify(presenter, atLeastOnce()).setSearch(any())
    }

    @Test
    fun verifyResult() {
        useCase.verifyResult(true)
        verify(presenter, atLeastOnce()).resultIsOk()
    }
}