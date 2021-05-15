package com.example.android.superhero.database.converters

import androidx.room.TypeConverter
import com.example.android.superhero.database.model.SuperHeroConnectionsDatabaseEntity
import com.google.gson.Gson

class SuperHeroConnectionsConverter {
    @TypeConverter
    fun fromString(value: String?): SuperHeroConnectionsDatabaseEntity? {
        val str: String = value ?: ""
        return Gson().fromJson<SuperHeroConnectionsDatabaseEntity>(
            str,
            SuperHeroConnectionsDatabaseEntity::class.java
        )
    }

    @TypeConverter
    fun fromList(connectionsDatabaseEntity: SuperHeroConnectionsDatabaseEntity?): String {
        val gson = Gson()
        return gson.toJson(connectionsDatabaseEntity)
    }
}