package com.example.android.superhero.network.responses

import com.example.android.superhero.database.model.SuperHeroWorkDatabaseEntity

class SuperHeroWorkNetworkEntity(
    var occupation: String,
    var base: String
) {
    fun toDatabaseWork(): SuperHeroWorkDatabaseEntity {
        return SuperHeroWorkDatabaseEntity(
            occupation = this.occupation,
            base = this.base
        )
    }
}