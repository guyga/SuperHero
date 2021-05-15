package com.example.android.superhero.database.converters

import androidx.room.TypeConverter
import com.example.android.superhero.database.model.SuperHeroWorkDatabaseEntity
import com.google.gson.Gson

class SuperHeroWorkConverter {
    @TypeConverter
    fun fromString(value: String?): SuperHeroWorkDatabaseEntity? {
        val str: String = value ?: ""
        return Gson().fromJson<SuperHeroWorkDatabaseEntity>(
            str,
            SuperHeroWorkDatabaseEntity::class.java
        )
    }

    @TypeConverter
    fun fromList(workDatabaseEntity: SuperHeroWorkDatabaseEntity?): String {
        val gson = Gson()
        return gson.toJson(workDatabaseEntity)
    }
}