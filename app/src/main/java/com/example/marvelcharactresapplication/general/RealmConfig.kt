package com.example.marvelcharactresapplication.general

import io.realm.RealmConfiguration

class RealmConfig {

    fun getRealmConfiguration(): RealmConfiguration{
        val config = RealmConfiguration.Builder()
        config.name("default2")
        config.schemaVersion(3)
        config.deleteRealmIfMigrationNeeded()
        return config.build()
    }
}