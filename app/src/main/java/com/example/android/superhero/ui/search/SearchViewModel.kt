package com.example.android.superhero.ui.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android.superhero.domain.model.SuperHero
import com.example.android.superhero.repository.SuperHeroRepository
import kotlinx.coroutines.launch

class SearchViewModel : ViewModel() {

    private val _superHeroRepository = SuperHeroRepository()

    private val _searchResults = MutableLiveData<List<SuperHero>?>()
    val searchResults: LiveData<List<SuperHero>?>
        get() = _searchResults

    private val _loadingSearchResults = MutableLiveData<Boolean>()
    val loadingSearchResults: LiveData<Boolean>
        get() = _loadingSearchResults

    private val _searchError = MutableLiveData<Boolean>()
    val searchError: LiveData<Boolean>
        get() = _searchError


    fun searchSuperHero(name: String) {
        viewModelScope.launch {
            _loadingSearchResults.value = true
            _searchError.value = false
            try {
                val heroesList = _superHeroRepository.searchSuperHero(name)
                _searchResults.value = heroesList ?: ArrayList()
            } catch (e: Exception) {
                _searchResults.value = null
                _searchError.value = true
            } finally {
                _loadingSearchResults.value = false
            }
        }
    }

}