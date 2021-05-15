package com.example.android.superhero.database.converters

import androidx.room.TypeConverter
import com.example.android.superhero.database.model.SuperHeroPowerStatsDatabaseEntity
import com.google.gson.Gson

class SuperHeroPowerStatsConverter {
    @TypeConverter
    fun fromString(value: String?): SuperHeroPowerStatsDatabaseEntity? {
        val str: String = value ?: ""
        return Gson().fromJson<SuperHeroPowerStatsDatabaseEntity>(
            str,
            SuperHeroPowerStatsDatabaseEntity::class.java
        )
    }

    @TypeConverter
    fun fromList(powerStatsDatabaseEntity: SuperHeroPowerStatsDatabaseEntity?): String {
        val gson = Gson()
        return gson.toJson(powerStatsDatabaseEntity)
    }
}