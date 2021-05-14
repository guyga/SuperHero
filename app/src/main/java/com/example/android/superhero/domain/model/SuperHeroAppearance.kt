package com.example.android.superhero.domain.model

data class SuperHeroAppearance(
    var gender: String,
    var race: String,
    var height: List<String>,
    var weight: List<String>,
    var eyeColor: String,
    var hairColor: String,
)