package com.example.android.superhero.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class SuperHeroAppearance(
    var gender: String,
    var race: String,
    var height: List<String>,
    var weight: List<String>,
    var eyeColor: String,
    var hairColor: String,
) : Parcelable