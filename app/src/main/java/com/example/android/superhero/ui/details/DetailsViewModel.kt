package com.example.android.superhero.ui.details

import android.content.res.Resources
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.android.superhero.domain.model.SuperHero

class DetailsViewModel : ViewModel() {

    private lateinit var _superHero: SuperHero

    private val _shareSuperHeroDetails = MutableLiveData<String?>()
    val shareSuperHeroDetails: LiveData<String?>
        get() = _shareSuperHeroDetails

    /**
     * Save the SuperHero
     */
    fun setSuperHero(superHero: SuperHero) {
        _superHero = superHero
    }

    /**
     * Generate some SuperHero details string for sharing
     */
    fun shareSuperHero(resources: Resources) {
        _shareSuperHeroDetails.value = _superHero.getSharingDetails(resources)
    }

    /**
     * Indicate sharing was performed
     */
    fun onCompleteSharingSuperHer() {
        _shareSuperHeroDetails.value = null
    }
}