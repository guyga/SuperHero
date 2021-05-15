package com.example.android.superhero.database.model

import com.example.android.superhero.domain.model.SuperHeroConnections

class SuperHeroConnectionsDatabaseEntity(
    var groupAffiliation: String,
    var relatives: String
) {
    fun toDomainConnections(): SuperHeroConnections {
        return SuperHeroConnections(
            groupAffiliation = this.groupAffiliation,
            relatives = this.relatives
        )
    }
}