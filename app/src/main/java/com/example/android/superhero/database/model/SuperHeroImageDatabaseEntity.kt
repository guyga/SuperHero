package com.example.android.superhero.database.model

import com.example.android.superhero.domain.model.SuperHeroImage

class SuperHeroImageDatabaseEntity(
    var url: String
) {
    fun toDomainImage(): SuperHeroImage {
        return SuperHeroImage(url = this.url)
    }
}