package com.example.android.superhero.repository

import android.content.Context
import android.util.Log
import com.example.android.superhero.database.SuperHeroDao
import com.example.android.superhero.database.SuperHeroDatabase
import com.example.android.superhero.domain.model.SuperHero
import com.example.android.superhero.network.SuperHeroServiceApi
import com.example.android.superhero.network.responses.toDomainSuperHeroes

class SuperHeroRepository private constructor(
    private val superHeroDao: SuperHeroDao
) {
    private val TAG = this.javaClass.simpleName

    /**
     * Search the superheroapi for results matching [name]
     */
    suspend fun searchSuperHero(name: String): List<SuperHero>? {
        Log.i(TAG, "Searching for $name")
        val response = SuperHeroServiceApi.superHeroApi.searchSuperHero(name)
        if (response.response == "success") {
            Log.i(TAG, "Searching for $name was successful")
        }
        return response.results?.toDomainSuperHeroes()
    }

    /**
     * Retrieve SuperHero by id.
     * Usable when knowing what SuperHero to look for, as opposed to [searchSuperHero] which can retrieve unpredictable results.
     * Will firstly attempt to retrieve the data locally and then through networking if not found
     */
    suspend fun getSuperHero(id: String): SuperHero? {
        val cachedSuperHero = superHeroDao.getSuperHero(id)
        if (cachedSuperHero != null) {
            Log.i(TAG, "hero $id was found in cache")
            return cachedSuperHero.toDomainSuperHero()
        }

        Log.i(TAG, "hero $id was not found in cache, retrieving from API")
        val response = SuperHeroServiceApi.superHeroApi.getSuperHero(id)
        if (response.response == "success") {
            Log.i(TAG, "hero $id was successfully retrieved")
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
                        SuperHeroRepository(
                            SuperHeroDatabase.getDatabase(context).superHeroDao()
                        )
                }
                return INSTANCE
            }
        }
    }
}