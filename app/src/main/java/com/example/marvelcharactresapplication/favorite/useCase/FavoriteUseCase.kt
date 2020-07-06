package com.example.marvelcharactresapplication.favorite.useCase

import com.example.marvelcharactresapplication.dto.CharactersDTO
import com.example.marvelcharactresapplication.favorite.presenter.contract.FavoritePresenterContract
import com.example.marvelcharactresapplication.favorite.useCase.contract.FavoriteUseCaseContract
import com.example.marvelcharactresapplication.general.RealmConfig
import com.example.marvelcharactresapplication.model.Data
import com.example.marvelcharactresapplication.model.Result
import com.example.marvelcharactresapplication.model.Thumbnail
import io.realm.Realm

class FavoriteUseCase(private val presenter: FavoritePresenterContract): FavoriteUseCaseContract {

    override fun getFavorites() {

        val realm = Realm.getInstance(RealmConfig().getRealmConfiguration())
        val favorites = realm.where(CharactersDTO::class.java).findAll()
        val results = mutableListOf<Result>()
        if(favorites.isNotEmpty()){
            for(fav in favorites){
                val tmb = Thumbnail(fav.path, fav.extension)
                val result = Result(fav.id, fav.name, fav.description, tmb)
                results.add(result)
            }
            val data = Data(results)
            presenter.setFavorite(data)
        }else{
            presenter.setNoItens()
        }
    }

    override fun checkResultOk(isOk: Boolean) {
        if(isOk){
            presenter.resultOk()
        }
    }
}