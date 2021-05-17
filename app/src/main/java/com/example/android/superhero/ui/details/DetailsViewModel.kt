package com.example.android.superhero.ui.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.android.superhero.domain.model.SuperHero

class DetailsViewModel : ViewModel() {

    private lateinit var _superHero: SuperHero

    private val _shareSuperHeroDetails = MutableLiveData<String?>()
    val shareSuperHeroDetails: LiveData<String?>
        get() = _shareSuperHeroDetails

    fun setSuperHero(superHero: SuperHero) {
        _superHero = superHero
    }

    fun shareSuperHero() {
        _shareSuperHeroDetails.value = _superHero.getSharingDetails()
    }

    fun onCompleteSharingSuperHer() {
        _shareSuperHeroDetails.value = null
    }
}