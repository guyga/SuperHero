package com.example.android.superhero.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.android.superhero.database.model.SuperHeroDatabaseEntity

@Dao
interface SuperHeroDao {

    @Query("SELECT * from SuperHeroDatabaseEntity WHERE name LIKE :name")
    suspend fun getSuperHeroes(name: String): List<SuperHeroDatabaseEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSuperHeroes(superHeroes: List<SuperHeroDatabaseEntity>)
}