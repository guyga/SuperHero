package com.example.android.superhero.database.converters

import androidx.room.TypeConverter
import com.example.android.superhero.database.model.SuperHeroAppearanceDatabaseEntity
import com.google.gson.Gson

class SuperHeroAppearanceConverter {
    @TypeConverter
    fun fromString(value: String?): SuperHeroAppearanceDatabaseEntity? {
        val str: String = value ?: ""
        return Gson().fromJson<SuperHeroAppearanceDatabaseEntity>(
            str,
            SuperHeroAppearanceDatabaseEntity::class.java
        )
    }

    @TypeConverter
    fun fromList(appearanceDatabaseEntity: SuperHeroAppearanceDatabaseEntity?): String {
        val gson = Gson()
        return gson.toJson(appearanceDatabaseEntity)
    }
}