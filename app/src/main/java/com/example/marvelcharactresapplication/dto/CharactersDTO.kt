package com.example.marvelcharactresapplication.dto

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class CharactersDTO(@PrimaryKey var id: Int = 0,
                         var name: String = "",
                         var description: String = "",
                         var thumbnail: String = "",
                         var path: String = "",
                         var extension: String = ""
                         ): RealmObject()