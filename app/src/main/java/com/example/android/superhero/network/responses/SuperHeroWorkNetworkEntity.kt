package com.example.android.superhero.network.responses

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
}