package com.example.android.superhero.ui.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.android.superhero.domain.ImageLoader

class DetailsViewModelFactory(private val imageLoader: ImageLoader) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DetailsViewModel::class.java)) {
            return DetailsViewModel(imageLoader) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}