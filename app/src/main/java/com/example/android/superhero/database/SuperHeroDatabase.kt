package com.example.android.superhero.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.android.superhero.database.converters.*
import com.example.android.superhero.database.model.SuperHeroDatabaseEntity
import com.example.android.superhero.database.model.UrlDatabaseEntity

@Database(entities = [SuperHeroDatabaseEntity::class, UrlDatabaseEntity::class], version = 2)
@TypeConverters(value = [StringListConverter::class, SuperHeroAppearanceConverter::class, SuperHeroBiographyConverter::class, SuperHeroConnectionsConverter::class, SuperHeroImageConverter::class, SuperHeroPowerStatsConverter::class, SuperHeroWorkConverter::class])
abstract class SuperHeroDatabase : RoomDatabase() {
    abstract fun superHeroDao(): SuperHeroDao

    companion object {
        private lateinit var INSTANCE: SuperHeroDatabase

        fun getDatabase(context: Context): SuperHeroDatabase {
            synchronized(this) {
                if (!::INSTANCE.isInitialized) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        SuperHeroDatabase::class.java,
                        "superhero-database"
                    ).build()
                }
            }
            return INSTANCE
        }
    }
}