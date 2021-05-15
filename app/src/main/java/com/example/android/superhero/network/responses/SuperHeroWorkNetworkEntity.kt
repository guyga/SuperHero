package com.example.android.superhero.network.responses

import com.example.android.superhero.database.model.SuperHeroWorkDatabaseEntity
import com.example.android.superhero.domain.model.SuperHeroWork

class SuperHeroWorkNetworkEntity(
    var occupation: String,
    var base: String
) {
    fun toDomainWork(): SuperHeroWork {
        return SuperHeroWork(
            occupation = this.occupation,
            base = this.base
        )
    }

    fun toDatabaseWork(): SuperHeroWorkDatabaseEntity {
        return SuperHeroWorkDatabaseEntity(
            occupation = this.occupation,
            base = this.base
        )
    }
}