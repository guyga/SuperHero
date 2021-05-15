package com.example.android.superhero.network.responses

import com.example.android.superhero.database.model.SuperHeroImageDatabaseEntity
import com.example.android.superhero.domain.model.SuperHeroImage

class SuperHeroImageNetworkEntity(
    var url: String
) {
    fun toDomainImage(): SuperHeroImage {
        return SuperHeroImage(url = this.url)
    }

    fun toDatabaseImage(): SuperHeroImageDatabaseEntity {
        return SuperHeroImageDatabaseEntity(url = this.url)
    }
}