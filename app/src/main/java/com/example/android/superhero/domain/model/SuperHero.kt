package com.example.android.superhero.domain.model

data class SuperHero(
    var id: String,
    var name: String,
    var powerstats: SuperHeroPowerStats,
    var biography: SuperHeroBiography,
    var appearance: SuperHeroAppearance,
    var work: SuperHeroWork,
    var connections: SuperHeroConnections,
    var image: SuperHeroImage
)