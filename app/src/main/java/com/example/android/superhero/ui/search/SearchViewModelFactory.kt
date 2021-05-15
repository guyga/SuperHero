package com.example.android.superhero.ui.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.android.superhero.repository.SuperHeroRepository

class SearchViewModelFactory(private val superHeroRepository: SuperHeroRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SearchViewModel::class.java)) {
            return SearchViewModel(superHeroRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}