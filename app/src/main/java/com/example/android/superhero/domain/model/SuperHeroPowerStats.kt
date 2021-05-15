package com.example.android.superhero.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class SuperHeroPowerStats(
    var intelligence: String,
    var strength: String,
    var speed: String,
    var durability: String,
    var power: String,
    var combat: String,
) : Parcelable