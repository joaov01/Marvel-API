package com.example.marvelcharactresapplication.favorite.ui

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import com.example.marvelcharactresapplication.R
import com.example.marvelcharactresapplication.favorite.listener.OnClickRemove
import com.example.marvelcharactresapplication.favorite.adapter.FavoriteAdapter
import com.example.marvelcharactresapplication.favorite.presenter.FavoritePresenter
import com.example.marvelcharactresapplication.favorite.presenter.contract.FavoritePresenterContract
import com.example.marvelcharactresapplication.favorite.ui.contract.FavoriteActivityContract
import com.example.marvelcharactresapplication.favorite.useCase.FavoriteUseCase
import com.example.marvelcharactresapplication.favorite.useCase.contract.FavoriteUseCaseContract
import com.example.marvelcharactresapplication.general.NUMBER_TEN
import com.example.marvelcharactresapplication.general.NUMBER_TWO
import com.example.marvelcharactresapplication.model.Data
import kotlinx.android.synthetic.main.activity_favorite.*

class FavoriteActivity : AppCompatActivity(), FavoriteActivityContract,
    OnClickRemove {

    private lateinit var adapter: FavoriteAdapter
    private lateinit var useCase: FavoriteUseCaseContract

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorite)
        createActionBar()
    }

    private fun createActionBar() {
        val actionBar = supportActionBar
        actionBar!!.title = getString(R.string.text_title_favorites)
        actionBar.setDisplayHomeAsUpEnabled(true)
        val presenter: FavoritePresenterContract = FavoritePresenter(this)
        useCase = FavoriteUseCase(presenter)
        useCase.getFavorites()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        setResult(Activity.RESULT_OK)
        finish()
        return super.onOptionsItemSelected(item)
    }

    override fun setFavorite(characters: Data) {
        adapter = FavoriteAdapter(characters, this, this)
        rvCharacteresFavorites.adapter = adapter
        rvCharacteresFavorites.visibility = View.VISIBLE
        llMsgDefault.visibility = View.GONE
        rvCharacteresFavorites.layoutManager = GridLayoutManager(this@FavoriteActivity, NUMBER_TWO)
    }

    override fun removeItem() {
        layoutNoItens()
    }

    override fun resultOk(){
        useCase.getFavorites()
    }

    override fun layoutNoItens() {
        rvCharacteresFavorites.visibility = View.GONE
        llMsgDefault.visibility = View.VISIBLE
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        useCase.checkResultOk(requestCode == NUMBER_TEN && resultCode == Activity.RESULT_OK)
        super.onActivityResult(requestCode, resultCode, data)
    }
}
