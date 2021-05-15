package com.example.android.superhero.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.android.superhero.database.model.SuperHeroDatabaseEntity

@Dao
interface SuperHeroDao {

    @Query("SELECT * from SuperHeroDatabaseEntity WHERE id = :id")
    suspend fun getSuperHero(id: String): SuperHeroDatabaseEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSuperHero(superHeroes: SuperHeroDatabaseEntity)
}