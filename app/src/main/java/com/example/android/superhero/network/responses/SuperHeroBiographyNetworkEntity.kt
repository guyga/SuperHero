package com.example.android.superhero.network.responses

import com.example.android.superhero.domain.model.SuperHeroBiography
import com.google.gson.annotations.SerializedName

class SuperHeroBiographyNetworkEntity(
    @SerializedName("full-name")
    var fullName: String,
    @SerializedName("alter-egos")
    var alterEgos: String,
    var aliases: List<String>,
    @SerializedName("place-of-birth")
    var placeOfBirth: String,
    @SerializedName("first-appearance")
    var firstAppearance: String,
    var publisher: String,
    var alignment: String,
) {

    fun toDomainBiography(): SuperHeroBiography {
        return SuperHeroBiography(
            fullName = this.fullName,
            alterEgos = this.alterEgos,
            aliases = this.aliases,
            placeOfBirth = this.placeOfBirth,
            firstAppearance = this.firstAppearance,
            publisher = this.publisher,
            alignment = this.alignment
        )
    }
}