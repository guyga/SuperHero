package com.example.android.superhero.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.android.superhero.database.model.SuperHeroDatabaseEntity
import com.example.android.superhero.database.model.UrlDatabaseEntity

@Dao
interface SuperHeroDao {

    @Query("SELECT * from SuperHeroDatabaseEntity WHERE id = :id")
    suspend fun getSuperHero(id: String): SuperHeroDatabaseEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSuperHero(superHeroes: SuperHeroDatabaseEntity)

    @Query("SELECT * from UrlDatabaseEntity WHERE url = :url")
    suspend fun getUrlWrapper(url: String): UrlDatabaseEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUrlWrapper(urlDatabaseEntity: UrlDatabaseEntity)
}