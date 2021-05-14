package com.example.android.superhero.domain.model

data class SuperHeroBiography(
    var fullName: String,
    var alterEgos: String,
    var aliases: List<String>,
    var placeOfBirth: String,
    var firstAppearance: String,
    var publisher: String,
    var alignment: String,
)