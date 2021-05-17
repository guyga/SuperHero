package com.example.android.superhero.ui.search

import com.example.android.superhero.domain.model.SuperHero

/**
 * A listener class to when a SuperHero is selected
 */
class OnSuperHeroSelectedListener(private val block: (superHero: SuperHero) -> Unit) {
    fun onSuperHeroSelected(superHero: SuperHero) = block(superHero)
}