package com.example.android.superhero.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class SuperHeroConnections(
    var groupAffiliation: String,
    var relatives: String
) : Parcelable