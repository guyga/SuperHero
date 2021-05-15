package com.example.android.superhero.network.responses

import com.example.android.superhero.database.model.SuperHeroConnectionsDatabaseEntity
import com.google.gson.annotations.SerializedName

class SuperHeroConnectionsNetworkEntity(
    @SerializedName("group-affiliation")
    var groupAffiliation: String,
    var relatives: String
) {
    fun toDatabaseConnections(): SuperHeroConnectionsDatabaseEntity {
        return SuperHeroConnectionsDatabaseEntity(
            groupAffiliation = this.groupAffiliation,
            relatives = this.relatives
        )
    }
}