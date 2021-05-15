package com.example.android.superhero.repository

import com.example.android.superhero.domain.model.SuperHero
import com.example.android.superhero.network.SuperHeroServiceApi
import com.example.android.superhero.network.responses.toDomainSuperHeroes

class SuperHeroRepository private constructor() {

    suspend fun searchSuperHero(name: String): List<SuperHero>? {
        val response = SuperHeroServiceApi.superHeroApi.searchSuperHero(name)
        return response.results?.toDomainSuperHeroes()
    }

    suspend fun getSuperHero(id: String): SuperHero? {
        val response = SuperHeroServiceApi.superHeroApi.getSuperHero(id)
        if (response.response == "success")
            return response.toDomainSuperHero()
        return null
    }

    companion object {
        var superHeroRepository = SuperHeroRepository()
    }
}