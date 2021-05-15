package com.example.android.superhero.database.model

import com.example.android.superhero.domain.model.SuperHeroWork

class SuperHeroWorkDatabaseEntity(
    var occupation: String,
    var base: String
) {
    fun toDomainWork(): SuperHeroWork {
        return SuperHeroWork(
            occupation = this.occupation,
            base = this.base
        )
    }
}