package com.example.android.superhero.network.responses

import com.example.android.superhero.domain.model.SuperHero

class SuperHeroNetworkEntity(
    var response: String,
    var id: String,
    var name: String,
    var powerstats: SuperHeroPowerStatsNetworkEntity,
    var biography: SuperHeroBiographyNetworkEntity,
    var appearance: SuperHeroAppearanceNetworkEntity,
    var work: SuperHeroWorkNetworkEntity,
    var connections: SuperHeroConnectionsNetworkEntity,
    var image: SuperHeroImageNetworkEntity
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

fun List<SuperHeroNetworkEntity>.toDomainSuperHeroes(): List<SuperHero> {
    return this.map { it.toDomainSuperHero() }
}

