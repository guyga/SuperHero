package com.example.android.superhero.domain.model

import android.content.res.Resources
import android.os.Parcelable
import com.example.android.superhero.R
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
) : Parcelable {

    fun getSharingDetails(res: Resources): String {
        val stringBuilder = StringBuilder()
        stringBuilder.append(name).appendLine()
            .append(res.getString(R.string.sharing_alignment, biography.alignment)).appendLine()
            .append(res.getString(R.string.sharing_alter_egos, biography.alterEgos)).appendLine()
            .append(res.getString(R.string.sharing_publisher, biography.publisher)).appendLine()
            .append(res.getString(R.string.sharing_image, image.url)).appendLine()
        return stringBuilder.toString()
    }

}