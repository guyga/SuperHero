package com.example.android.superhero.network.responses

import com.example.android.superhero.database.model.SuperHeroDatabaseEntity

class SuperHeroNetworkEntity(
    var id: String,
    var name: String,
    var powerstats: SuperHeroPowerStatsNetworkEntity,
    var biography: SuperHeroBiographyNetworkEntity,
    var appearance: SuperHeroAppearanceNetworkEntity,
    var work: SuperHeroWorkNetworkEntity,
    var connections: SuperHeroConnectionsNetworkEntity,
    var image: SuperHeroImageNetworkEntity
)

fun List<SuperHeroNetworkEntity>.toDatabaseSuperHeroes(): List<SuperHeroDatabaseEntity> {
    return this.map { superHeroNetworkEntity ->
        SuperHeroDatabaseEntity(
            id = superHeroNetworkEntity.id,
            name = superHeroNetworkEntity.name,
            powerstats = superHeroNetworkEntity.powerstats.toDatabasePowerStats(),
            biography = superHeroNetworkEntity.biography.toDatabaseBiography(),
            appearance = superHeroNetworkEntity.appearance.toDatabaseAppearance(),
            work = superHeroNetworkEntity.work.toDatabaseWork(),
            connections = superHeroNetworkEntity.connections.toDatabaseConnections(),
            image = superHeroNetworkEntity.image.toDatabaseImage()
        )
    }
}