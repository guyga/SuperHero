package com.example.android.superhero.ui.details

import android.content.res.Resources
import android.util.Log
import android.widget.ImageView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android.superhero.domain.ImageLoader
import com.example.android.superhero.domain.model.SuperHero
import kotlinx.coroutines.launch

class DetailsViewModel(private var imageLoader: ImageLoader) : ViewModel() {
    private val TAG = this.javaClass.simpleName
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

    fun loadImage(image: ImageView) {
        viewModelScope.launch {
            try {
                imageLoader.loadImage(image, _superHero.image.url)
            }
            catch (e: Exception){
                Log.e(TAG, "Loading image as failed ${e.message}")
            }
        }
    }
}