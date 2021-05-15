package com.example.android.superhero.database.converters

import androidx.room.TypeConverter
import com.example.android.superhero.database.model.SuperHeroImageDatabaseEntity
import com.google.gson.Gson

class SuperHeroImageConverter {
    @TypeConverter
    fun fromString(value: String?): SuperHeroImageDatabaseEntity? {
        val str: String = value ?: ""
        return Gson().fromJson<SuperHeroImageDatabaseEntity>(
            str,
            SuperHeroImageDatabaseEntity::class.java
        )
    }

    @TypeConverter
    fun fromList(imageDatabaseEntity: SuperHeroImageDatabaseEntity?): String {
        val gson = Gson()
        return gson.toJson(imageDatabaseEntity)
    }
}