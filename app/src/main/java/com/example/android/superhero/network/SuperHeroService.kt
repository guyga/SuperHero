package com.example.android.superhero.network

import com.example.android.superhero.BuildConfig
import com.example.android.superhero.network.responses.SuperHeroSearchResponse
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface SuperHeroService {
    @GET("search/{name}")
    suspend fun searchSuperHero(@Path("name") name: String): SuperHeroSearchResponse
}

object SuperHeroServiceApi{
    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("https://superheroapi.com/api/${BuildConfig.SUPERHERO_API_KEY}/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val superHeroApi:SuperHeroService = retrofit.create(SuperHeroService::class.java)
}