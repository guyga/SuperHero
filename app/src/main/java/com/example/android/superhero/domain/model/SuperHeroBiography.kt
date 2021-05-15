package com.example.android.superhero.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class SuperHeroBiography(
    var fullName: String,
    var alterEgos: String,
    var aliases: List<String>,
    var placeOfBirth: String,
    var firstAppearance: String,
    var publisher: String,
    var alignment: String,
) : Parcelable