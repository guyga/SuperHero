package com.example.android.superhero.network.responses

import com.example.android.superhero.database.model.SuperHeroAppearanceDatabaseEntity
import com.google.gson.annotations.SerializedName

class SuperHeroAppearanceNetworkEntity(
    var gender: String,
    var race: String,
    var height: List<String>,
    var weight: List<String>,
    @SerializedName("eye-color")
    var eyeColor: String,
    @SerializedName("hair-color")
    var hairColor: String,
) {
    fun toDatabaseAppearance(): SuperHeroAppearanceDatabaseEntity {
        return SuperHeroAppearanceDatabaseEntity(
            gender = this.gender,
            race = this.race,
            height = this.height,
            weight = this.weight,
            eyeColor = this.eyeColor,
            hairColor = this.hairColor
        )
    }
}