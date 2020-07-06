package com.example.marvelcharactresapplication.home.useCase

import android.annotation.SuppressLint
import com.example.marvelcharactresapplication.home.presenter.contract.HomePresenterContract
import com.example.marvelcharactresapplication.home.useCase.contract.HomeUseCaseContract
import com.example.marvelcharactresapplication.model.Characters
import com.example.marvelcharactresapplication.model.Data
import com.example.marvelcharactresapplication.model.Result
import com.example.marvelcharactresapplication.repository.Repository
import com.example.marvelcharactresapplication.repository.contract.ServiceCallback

class HomeUseCase(private val presenter: HomePresenterContract, private val repository: Repository): HomeUseCaseContract {

    override fun getCharacters() {
        repository.getCharacters(object : ServiceCallback<Characters>{
            override fun callback(response: Characters) {
                presenter.setCharacters(response)
            }

        }, object : ServiceCallback<String>{
            override fun callback(response: String) {
                presenter.setError(response)
            }
        })
    }

    override fun initComponents() {
        presenter.initComponents()
    }

    @SuppressLint("DefaultLocale")
    override fun search(characters: Data, s: String) {
        val searchCharacters = mutableListOf<Result>()
            for(result in characters.results){
                if(result.name.toLowerCase().contains(s.toLowerCase())){
                    searchCharacters.add(result)
                }
            }
            val data = Data(searchCharacters)
            presenter.setSearch(data)
        }

    override fun verifyResult(isOk: Boolean) {
        if(isOk){
            presenter.resultIsOk()
        }
    }
}