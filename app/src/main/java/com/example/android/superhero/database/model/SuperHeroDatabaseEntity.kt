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
)

fun List<SuperHeroDatabaseEntity>.toDomainSuperHeroes(): List<SuperHero> {
    return this.map { superHeroDatabaseEntity ->
        SuperHero(
            id = superHeroDatabaseEntity.id,
            name = superHeroDatabaseEntity.name,
            powerstats = superHeroDatabaseEntity.powerstats.toDomainPowerStats(),
            biography = superHeroDatabaseEntity.biography.toDomainBiography(),
            appearance = superHeroDatabaseEntity.appearance.toDomainAppearance(),
            work = superHeroDatabaseEntity.work.toDomainWork(),
            connections = superHeroDatabaseEntity.connections.toDomainConnections(),
            image = superHeroDatabaseEntity.image.toDomainImage()
        )
    }
}