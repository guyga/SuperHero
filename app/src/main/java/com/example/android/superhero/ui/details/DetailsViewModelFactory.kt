package com.example.android.superhero.ui.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.android.superhero.repository.SuperHeroRepository

class DetailsViewModelFactory(private val superHeroRepository: SuperHeroRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DetailsViewModel::class.java)) {
            return DetailsViewModel(superHeroRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}