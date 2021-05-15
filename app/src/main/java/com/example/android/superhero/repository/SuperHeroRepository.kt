package com.example.android.superhero.repository

import android.content.Context
import com.example.android.superhero.database.SuperHeroDao
import com.example.android.superhero.database.SuperHeroDatabase
import com.example.android.superhero.database.model.toDomainSuperHeroes
import com.example.android.superhero.domain.model.SuperHero
import com.example.android.superhero.network.SuperHeroServiceApi
import com.example.android.superhero.network.responses.toDatabaseSuperHeroes
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class SuperHeroRepository private constructor(
    private var superHeroDao: SuperHeroDao
) {

    suspend fun searchSuperHero(name: String): List<SuperHero>? {
        return withContext(Dispatchers.IO) {
            val query = "%$name%"
            val cachedResponse = superHeroDao.getSuperHeroes(query)
            if (!cachedResponse.isNullOrEmpty())
                return@withContext cachedResponse.toDomainSuperHeroes()

            val response = SuperHeroServiceApi.superHeroApi.searchSuperHero(name)
            if (response.results != null)
                superHeroDao.insertSuperHeroes(response.results!!.toDatabaseSuperHeroes())
            return@withContext superHeroDao.getSuperHeroes(query).toDomainSuperHeroes()
        }
    }

    companion object {
        private lateinit var INSTANCE: SuperHeroRepository

        fun getInstance(context: Context): SuperHeroRepository {
            synchronized(this) {
                if (!::INSTANCE.isInitialized) {
                    INSTANCE = SuperHeroRepository(
                        SuperHeroDatabase.getDatabase(context).superHeroDao()
                    )
                }
                return INSTANCE
            }
        }
    }
}