package com.example.android.superhero.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class SuperHero(
    var id: String,
    var name: String,
    var powerstats: SuperHeroPowerStats,
    var biography: SuperHeroBiography,
    var appearance: SuperHeroAppearance,
    var work: SuperHeroWork,
    var connections: SuperHeroConnections,
    var image: SuperHeroImage
) : Parcelable{

    fun getSharingDetails():String{
        val stringBuilder = StringBuilder()
        stringBuilder.append(name).appendLine()
            .append("Alignment is ").append(biography.alignment).appendLine()
            .append("Alter Egos: ").append(biography.alterEgos).appendLine()
            .append("Published by: ").append(biography.publisher).appendLine()
            .append("Image url: ").append(image.url).appendLine()
        return stringBuilder.toString()
    }

}