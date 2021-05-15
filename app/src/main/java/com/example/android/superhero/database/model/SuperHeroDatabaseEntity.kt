package com.example.android.superhero.database.model

import androidx.room.Entity
import androidx.room.TypeConverters
import com.example.android.superhero.database.converters.*
import com.example.android.superhero.domain.model.SuperHero

@Entity(primaryKeys = [("id")])
class SuperHeroDatabaseEntity(
    var id: String,
    var name: String,
    @TypeConverters(SuperHeroPowerStatsConverter::class)
    var powerstats: SuperHeroPowerStatsDatabaseEntity,
    @TypeConverters(SuperHeroBiographyConverter::class)
    var biography: SuperHeroBiographyDatabaseEntity,
    @TypeConverters(SuperHeroAppearanceConverter::class)
    var appearance: SuperHeroAppearanceDatabaseEntity,
    @TypeConverters(SuperHeroWorkConverter::class)
    var work: SuperHeroWorkDatabaseEntity,
    @TypeConverters(SuperHeroConnectionsConverter::class)
    var connections: SuperHeroConnectionsDatabaseEntity,
    @TypeConverters(SuperHeroImageConverter::class)
    var image: SuperHeroImageDatabaseEntity
) {
    fun toDomainSuperHero(): SuperHero {
        return SuperHero(
            id = this.id,
            name = this.name,
            powerstats = this.powerstats.toDomainPowerStats(),
            biography = this.biography.toDomainBiography(),
            appearance = this.appearance.toDomainAppearance(),
            work = this.work.toDomainWork(),
            connections = this.connections.toDomainConnections(),
            image = this.image.toDomainImage()
        )
    }
}

fun List<SuperHeroDatabaseEntity>.toDomainSuperHeroes(): List<SuperHero> {
    return this.map { it.toDomainSuperHero() }
}