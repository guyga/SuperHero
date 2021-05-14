package com.example.android.superhero.network.responses

import com.example.android.superhero.domain.model.SuperHero

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

fun List<SuperHeroNetworkEntity>.toDomainSuperHeroes(): List<SuperHero> {
    return this.map { superHeroNetworkEntity ->
        SuperHero(
            id = superHeroNetworkEntity.id,
            name = superHeroNetworkEntity.name,
            powerstats = superHeroNetworkEntity.powerstats.toDomainPowerStats(),
            biography = superHeroNetworkEntity.biography.toDomainBiography(),
            appearance = superHeroNetworkEntity.appearance.toDomainAppearance(),
            work = superHeroNetworkEntity.work.toDomainWork(),
            connections = superHeroNetworkEntity.connections.toDomainConnections(),
            image = superHeroNetworkEntity.image.toDomainImage()
        )
    }
}