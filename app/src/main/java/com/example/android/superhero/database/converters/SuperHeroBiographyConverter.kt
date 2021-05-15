package com.example.android.superhero.database.converters

import androidx.room.TypeConverter
import com.example.android.superhero.database.model.SuperHeroBiographyDatabaseEntity
import com.google.gson.Gson

class SuperHeroBiographyConverter {
    @TypeConverter
    fun fromString(value: String?): SuperHeroBiographyDatabaseEntity? {
        val str: String = value ?: ""
        return Gson().fromJson<SuperHeroBiographyDatabaseEntity>(
            str,
            SuperHeroBiographyDatabaseEntity::class.java
        )
    }

    @TypeConverter
    fun fromList(biographyDatabaseEntity: SuperHeroBiographyDatabaseEntity?): String {
        val gson = Gson()
        return gson.toJson(biographyDatabaseEntity)
    }
}