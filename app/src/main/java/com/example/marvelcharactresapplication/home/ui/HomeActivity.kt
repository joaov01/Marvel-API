package com.example.marvelcharactresapplication.home.ui

import android.app.Activity
import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.example.marvelcharactresapplication.R
import com.example.marvelcharactresapplication.favorite.ui.FavoriteActivity
import com.example.marvelcharactresapplication.general.MarvelLoading
import com.example.marvelcharactresapplication.general.NUMBER_TEN
import com.example.marvelcharactresapplication.general.NUMBER_TWO
import com.example.marvelcharactresapplication.home.adapter.CharacterAdapter
import com.example.marvelcharactresapplication.home.presenter.HomePresenter
import com.example.marvelcharactresapplication.home.presenter.contract.HomePresenterContract
import com.example.marvelcharactresapplication.home.ui.contract.HomeActivityContract
import com.example.marvelcharactresapplication.home.useCase.HomeUseCase
import com.example.marvelcharactresapplication.home.useCase.contract.HomeUseCaseContract
import com.example.marvelcharactresapplication.model.Characters
import com.example.marvelcharactresapplication.model.Data
import com.example.marvelcharactresapplication.repository.Repository
import io.realm.Realm
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity(), HomeActivityContract {

    private lateinit var presenter: HomePresenterContract
    private lateinit var useCase: HomeUseCaseContract
    private var characters: Data = Data(mutableListOf())
    private lateinit var adapter: CharacterAdapter
    private lateinit var dialog: Dialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        Realm.init(this)
        initServices()
    }

    private fun initServices() {
        dialog = MarvelLoading().show(this)
        presenter = HomePresenter(this)
        useCase = HomeUseCase(presenter, Repository())
        useCase.getCharacters()
    }

     override fun initComponents() {
        ibFavorite.setOnClickListener{
            startActivityForResult(Intent(this, FavoriteActivity::class.java), NUMBER_TEN)
        }

        etSearch.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {

            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                useCase.search(this@HomeActivity.characters, p0.toString())
            }

        })
        dialog.dismiss()
     }

    override fun setError(error: String) {
        dialog.dismiss()
        val builder = AlertDialog.Builder(this)
        builder.setMessage(error + " " + getString(R.string.text_send_to_favorites))
        builder.setNegativeButton(getString(R.string.text_no)) { _, _ -> finish()}
        builder.setPositiveButton(getString(R.string.text_yes)) { _, _ ->
            startActivityForResult(Intent(this@HomeActivity,
                FavoriteActivity::class.java), NUMBER_TEN)
            finish()}
        val alertDialog = builder.create()
        alertDialog.show()
    }

    override fun setCharacters(characters: Characters) {
        nsMain.visibility = View.VISIBLE
        tvNotFound.visibility = View.GONE
        this.characters = characters.data
        adapter = CharacterAdapter(characters.data, this@HomeActivity)
        rvCharacteres.adapter = adapter
        rvCharacteres.setHasFixedSize(true)
        rvCharacteres.layoutManager = GridLayoutManager(this@HomeActivity, NUMBER_TWO)
        useCase.initComponents()
    }

    override fun setSearchNoResult() {
        nsMain.visibility = View.GONE
        tvNotFound.visibility = View.VISIBLE
    }

    override fun setSearch(data: Data) {
        nsMain.visibility = View.VISIBLE
        tvNotFound.visibility = View.GONE
        adapter = CharacterAdapter(data, this@HomeActivity)
        rvCharacteres.adapter = adapter
        adapter.notifyDataSetChanged()
    }

    override fun reloadCharacters(){
        adapter.notifyDataSetChanged()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        useCase.verifyResult(requestCode == NUMBER_TEN && resultCode == Activity.RESULT_OK)
        super.onActivityResult(requestCode, resultCode, data)
    }
}
