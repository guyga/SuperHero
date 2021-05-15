package com.example.android.superhero.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class SuperHeroWork(
    var occupation: String,
    var base: String
) : Parcelable