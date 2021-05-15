package com.example.android.superhero.network.responses

import com.example.android.superhero.database.model.SuperHeroImageDatabaseEntity

class SuperHeroImageNetworkEntity(
    var url: String
) {
    fun toDatabaseImage(): SuperHeroImageDatabaseEntity {
        return SuperHeroImageDatabaseEntity(url = this.url)
    }
}