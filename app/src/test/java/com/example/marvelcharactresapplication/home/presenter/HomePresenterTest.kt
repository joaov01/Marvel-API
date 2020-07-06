package com.example.marvelcharactresapplication.home.presenter

import com.example.marvelcharactresapplication.general.MSG_UNIT_TEST
import com.example.marvelcharactresapplication.home.ui.contract.HomeActivityContract
import com.example.marvelcharactresapplication.model.Characters
import com.example.marvelcharactresapplication.model.Data
import com.nhaarman.mockitokotlin2.atLeastOnce
import com.nhaarman.mockitokotlin2.verify
import org.junit.Test

import org.junit.Assert.*
import org.mockito.Mockito

class HomePresenterTest {

    private val view = Mockito.mock(HomeActivityContract::class.java)
    private val presenter = HomePresenter(view)
    private val characters = Mockito.mock(Characters::class.java)
    private val data = Mockito.mock(Data::class.java)

    @Test
    fun setCharacters() {
        presenter.setCharacters(characters)
        verify(view, atLeastOnce()).setCharacters(characters)
    }

    @Test
    fun setError() {
        val error = MSG_UNIT_TEST
        presenter.setError(error)
        verify(view, atLeastOnce()).setError(error)
    }

    @Test
    fun setSearch() {
        presenter.setSearch(data)
        verify(view, atLeastOnce()).setSearchNoResult()
    }

    @Test
    fun initComponents() {
        presenter.initComponents()
        verify(view, atLeastOnce()).initComponents()
    }

    @Test
    fun resultIsOk() {
        presenter.resultIsOk()
        verify(view, atLeastOnce()).reloadCharacters()
    }
}