package com.example.marvelcharactresapplication.details.ui

import android.app.Activity
import android.app.Dialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.example.marvelcharactresapplication.R
import com.example.marvelcharactresapplication.details.presenter.DetailsPresenter
import com.example.marvelcharactresapplication.details.presenter.contract.DetailsPresenterContract
import com.example.marvelcharactresapplication.details.ui.contract.DetailsActivityContract
import com.example.marvelcharactresapplication.details.useCase.DetailsUseCase
import com.example.marvelcharactresapplication.details.useCase.contract.DetailsUseCaseContract
import com.example.marvelcharactresapplication.dto.CharactersDTO
import com.example.marvelcharactresapplication.general.MarvelLoading
import com.example.marvelcharactresapplication.general.RealmConfig
import com.example.marvelcharactresapplication.model.Result
import io.realm.Realm
import kotlinx.android.synthetic.main.activity_details.*

class DetailsActivity : AppCompatActivity(), DetailsActivityContract {

    private lateinit var character: Result
    private lateinit var img: String
    private val realm = Realm.getInstance(RealmConfig().getRealmConfiguration())
    private lateinit var presenter: DetailsPresenterContract
    private lateinit var useCase: DetailsUseCaseContract
    private lateinit var dialog: Dialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        dialog = MarvelLoading().show(this)
        presenter = DetailsPresenter(this)
        useCase = DetailsUseCase(presenter)
        useCase.createMenu()
    }

    override fun createMenu() {
        val actionBar = supportActionBar
        actionBar!!.title = getString(R.string.text_title_details)
        actionBar.setDisplayHomeAsUpEnabled(true)
        useCase.verifyIntent(intent)
    }

    override
    fun setIntent(intent: Intent){
        val intent = intent
        character = intent.getSerializableExtra(getString(R.string.text_tag_characters)) as Result
        useCase.initComponents()
    }

    override fun initComponents() {
        val characters = realm.where(CharactersDTO::class.java).equalTo("id", character.id).findFirst()
        img = character.thumbnail.path + "." + character.thumbnail.extension
        tvName.text = character.name
        tvDescription.text = character.description
        Glide.with(this).load(img).into(ivCharacter)
        useCase.verifyFavorites(characters)
    }

    override fun noFavorite() {
        ivFavorite.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_favorite_border_black_24dp))
        ivFavorite.setOnClickListener{
            val characterB = CharactersDTO(character.id,
                character.name,
                character.description,
                img,
                character.thumbnail.path,
                character.thumbnail.extension)
            realm.beginTransaction()
            realm.copyToRealm(characterB)
            realm.commitTransaction()
            ivFavorite.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_favorite_black_24dp))
        }
        dialog.dismiss()
    }

    override fun favorite(characters: CharactersDTO) {
        ivFavorite.setOnClickListener{
            realm.beginTransaction()
            characters.deleteFromRealm()
            realm.commitTransaction()
            ivFavorite.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_favorite_border_black_24dp))
        }
        dialog.dismiss()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        setResult(Activity.RESULT_OK)
        finish()
        return super.onOptionsItemSelected(item)
    }

}
