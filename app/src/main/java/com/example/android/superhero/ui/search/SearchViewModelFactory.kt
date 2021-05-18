package com.example.android.superhero.ui.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.android.superhero.domain.ImageLoader
import com.example.android.superhero.repository.SuperHeroRepository

class SearchViewModelFactory(
    private val superHeroRepository: SuperHeroRepository,
    private val imageLoader: ImageLoader
) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SearchViewModel::class.java)) {
            return SearchViewModel(superHeroRepository, imageLoader) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}