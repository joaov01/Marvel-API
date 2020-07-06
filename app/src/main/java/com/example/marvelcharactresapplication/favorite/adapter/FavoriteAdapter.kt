package com.example.marvelcharactresapplication.favorite.adapter

import android.app.Activity
import android.app.Activity.RESULT_OK
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.marvelcharactresapplication.R
import com.example.marvelcharactresapplication.details.ui.DetailsActivity
import com.example.marvelcharactresapplication.dto.CharactersDTO
import com.example.marvelcharactresapplication.favorite.listener.OnClickRemove
import com.example.marvelcharactresapplication.general.NUMBER_ONE
import com.example.marvelcharactresapplication.general.NUMBER_TEN
import com.example.marvelcharactresapplication.general.RealmConfig
import com.example.marvelcharactresapplication.home.adapter.CharacterAdapter
import com.example.marvelcharactresapplication.model.Data
import io.realm.Realm

class FavoriteAdapter(private val data: Data, private val activity: Activity, private val listener: OnClickRemove): RecyclerView.Adapter<CharacterAdapter.CharacterHolder>(){

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CharacterAdapter.CharacterHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_characters_layout, parent, false)
        return CharacterAdapter.CharacterHolder(view)
    }

    override fun getItemCount(): Int {
        return data.results.size
    }

    override fun onBindViewHolder(holder: CharacterAdapter.CharacterHolder, position: Int) {
        val realm = Realm.getInstance(RealmConfig().getRealmConfiguration())
        val query = realm.where(CharactersDTO::class.java).equalTo("id", data.results[position].id).findFirst()
        val img = data.results[position].thumbnail.path.replace("http:", "https:") +
                "." + data.results[position].thumbnail.extension
        if(query == null){
            holder.ivFavorite.setImageDrawable(ContextCompat.getDrawable(activity, R.drawable.ic_favorite_border_black_24dp))
            holder.ivFavorite.setOnClickListener{
                val character = CharactersDTO(data.results[position].id,
                    data.results[position].name,
                    data.results[position].description,
                    img,
                    data.results[position].thumbnail.path,
                    data.results[position].thumbnail.extension)
                realm.beginTransaction()
                realm.copyToRealm(character)
                realm.commitTransaction()
                this.notifyDataSetChanged()
            }
        }else{
            holder.ivFavorite.setImageDrawable(ContextCompat.getDrawable(activity, R.drawable.ic_favorite_black_24dp))
            holder.ivFavorite.setOnClickListener{
                realm.beginTransaction()
                query.deleteFromRealm()
                realm.commitTransaction()
                this.notifyDataSetChanged()
                activity.setResult(RESULT_OK)
                data.results.removeAt(position)
                layoutScreen()
            }
        }

        Glide.with(activity).load(img).into(holder.ivItem)
        holder.tvTitle.text = data.results[position].name
        holder.clMain.setOnClickListener {
            val intent = Intent(activity, DetailsActivity::class.java)
            intent.putExtra(activity.getString(R.string.text_tag_characters), data.results[position])
            activity.startActivityForResult(intent, NUMBER_TEN)
        }
    }

    private fun layoutScreen() {
        if (data.results.size < NUMBER_ONE) {
            listener.removeItem()
        }
    }


}