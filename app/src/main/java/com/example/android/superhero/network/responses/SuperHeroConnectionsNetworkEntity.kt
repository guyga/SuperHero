package com.example.android.superhero.network.responses

import com.example.android.superhero.domain.model.SuperHeroConnections
import com.google.gson.annotations.SerializedName

class SuperHeroConnectionsNetworkEntity(
    @SerializedName("group-affiliation")
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