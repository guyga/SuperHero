package com.example.android.superhero.database.model

import androidx.room.TypeConverters
import com.example.android.superhero.database.converters.StringListConverter
import com.example.android.superhero.domain.model.SuperHeroBiography

class SuperHeroBiographyDatabaseEntity(
    var fullName: String,
    var alterEgos: String,
    @TypeConverters(StringListConverter::class)
    var aliases: List<String>,
    var placeOfBirth: String,
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