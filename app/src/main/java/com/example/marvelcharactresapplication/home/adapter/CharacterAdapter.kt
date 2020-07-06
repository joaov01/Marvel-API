package com.example.marvelcharactresapplication.home.adapter

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.marvelcharactresapplication.R
import com.example.marvelcharactresapplication.details.ui.DetailsActivity
import com.example.marvelcharactresapplication.dto.CharactersDTO
import com.example.marvelcharactresapplication.general.NUMBER_TEN
import com.example.marvelcharactresapplication.general.RealmConfig
import com.example.marvelcharactresapplication.model.Data
import io.realm.Realm

open class CharacterAdapter(private val characters: Data, private val activity: Activity):
    RecyclerView.Adapter<CharacterAdapter.CharacterHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_characters_layout, parent, false)
        return CharacterHolder(view)
    }

    override fun getItemCount(): Int {
        return characters.results.size
    }

    override fun onBindViewHolder(holder: CharacterHolder, position: Int) {
        val realm = Realm.getInstance(RealmConfig().getRealmConfiguration())
        val query = realm.where(CharactersDTO::class.java).equalTo("id", characters.results[position].id).findFirst()
        val img = characters.results[position].thumbnail.path.replace("http:", "https:") +
                "." + characters.results[position].thumbnail.extension

        if(query == null){
            holder.ivFavorite.setImageDrawable(ContextCompat.getDrawable(activity, R.drawable.ic_favorite_border_black_24dp))
            holder.ivFavorite.setOnClickListener{
                val character = CharactersDTO(characters.results[position].id,
                    characters.results[position].name,
                    characters.results[position].description,
                    img,
                    characters.results[position].thumbnail.path,
                    characters.results[position].thumbnail.extension)
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
            }
        }

        Glide.with(activity).load(img).into(holder.ivItem)
        holder.tvTitle.text = characters.results[position].name
        holder.clMain.setOnClickListener { 
            val intent = Intent(activity, DetailsActivity::class.java)
            intent.putExtra(activity.getString(R.string.text_tag_characters), characters.results[position])
            activity.startActivityForResult(intent, NUMBER_TEN)
        }
    }

    class CharacterHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var ivItem: ImageView = itemView.findViewById(R.id.ivItem)
        var tvTitle: TextView = itemView.findViewById(R.id.tvTitle)
        var ivFavorite: ImageView = itemView.findViewById(R.id.ivFavorite)
        var clMain: ConstraintLayout = itemView.findViewById(R.id.clMain)
    }
}