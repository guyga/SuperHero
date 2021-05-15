package com.example.android.superhero.database.model

import androidx.room.TypeConverters
import com.example.android.superhero.database.converters.StringListConverter
import com.example.android.superhero.domain.model.SuperHeroAppearance

class SuperHeroAppearanceDatabaseEntity(
    var gender: String,
    var race: String,
    @TypeConverters(StringListConverter::class)
    var height: List<String>,
    @TypeConverters(StringListConverter::class)
    var weight: List<String>,
    var eyeColor: String,
    var hairColor: String,
) {
    fun toDomainAppearance(): SuperHeroAppearance {
        return SuperHeroAppearance(
            gender = this.gender,
            race = this.race,
            height = this.height,
            weight = this.weight,
            eyeColor = this.eyeColor,
            hairColor = this.hairColor
        )
    }
}