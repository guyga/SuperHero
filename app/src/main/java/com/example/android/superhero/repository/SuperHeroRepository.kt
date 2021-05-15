package com.example.android.superhero.repository

import android.content.Context
import com.example.android.superhero.database.SuperHeroDao
import com.example.android.superhero.database.SuperHeroDatabase
import com.example.android.superhero.domain.model.SuperHero
import com.example.android.superhero.network.SuperHeroServiceApi
import com.example.android.superhero.network.responses.toDomainSuperHeroes

class SuperHeroRepository private constructor(
    private val superHeroDao: SuperHeroDao
) {

    suspend fun searchSuperHero(name: String): List<SuperHero>? {
        val response = SuperHeroServiceApi.superHeroApi.searchSuperHero(name)
        return response.results?.toDomainSuperHeroes()
    }

    suspend fun getSuperHero(id: String): SuperHero? {
        val cachedSuperHero = superHeroDao.getSuperHero(id)
        if (cachedSuperHero != null)
            return cachedSuperHero.toDomainSuperHero()

        val response = SuperHeroServiceApi.superHeroApi.getSuperHero(id)
        if (response.response == "success") {
            superHeroDao.insertSuperHero(response.toDatabaseSuperHero())
            return response.toDomainSuperHero()
        }
        return null
    }

    companion object {
        private lateinit var INSTANCE: SuperHeroRepository

        fun getInstance(context: Context): SuperHeroRepository {
            synchronized(this) {
                if (!::INSTANCE.isInitialized) {
                    INSTANCE =
                        SuperHeroRepository(SuperHeroDatabase.getDatabase(context).superHeroDao())
                }
                return INSTANCE
            }
        }
    }
}