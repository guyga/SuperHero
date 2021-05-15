package com.example.android.superhero.ui.search

import androidx.lifecycle.*
import com.example.android.superhero.domain.model.SuperHero
import com.example.android.superhero.repository.SuperHeroRepository
import kotlinx.coroutines.launch

class SearchViewModel(private val superHeroRepository: SuperHeroRepository) : ViewModel() {

    private val _searchResults = MutableLiveData<List<SuperHero>?>()
    val searchResults: LiveData<List<SuperHero>?>
        get() = _searchResults

    private val _loadingSearchResults = MutableLiveData<Boolean>()
    val loadingSearchResults: LiveData<Boolean>
        get() = _loadingSearchResults

    private val _searchError = MutableLiveData<Boolean>()
    val searchError: LiveData<Boolean>
        get() = _searchError

    private val _recommendationsUnfiltered = MutableLiveData<ArrayList<SuperHero>?>()

    private val _recommendationsFiltered = MediatorLiveData<ArrayList<SuperHero>?>()
    val recommendationsFiltered: LiveData<ArrayList<SuperHero>?>
        get() = _recommendationsFiltered

    private val _loadingRecommendations = MutableLiveData<Boolean>()
    val loadingRecommendations: LiveData<Boolean>
        get() = _loadingRecommendations

    init {

        _recommendationsFiltered.addSource(_recommendationsUnfiltered) {
            _recommendationsFiltered.value = getFilteredRecommendations(null)
        }
        _recommendationsFiltered.addSource(_searchResults) { results ->
            results?.let {
                var recommendations = _recommendationsUnfiltered.value
                if (recommendations != null) {
                    for (superHero in recommendations!!) {
                        if (it.contains(superHero)) {
                            recommendations = getFilteredRecommendations(superHero)
                            break
                        }
                    }
                }
                _recommendationsFiltered.value = recommendations
            }
        }

        fetchRecommendations()
    }

    private fun fetchRecommendations() {
        _loadingRecommendations.value = true
        viewModelScope.launch {
            try {
                val etrigan = superHeroRepository.getSuperHero("246")!!
                val penguin = superHeroRepository.getSuperHero("514")!!
                val arsenal = superHeroRepository.getSuperHero("46")!!
                val hiroNakamura = superHeroRepository.getSuperHero("326")!!

                val recommendations = arrayListOf<SuperHero>(
                    etrigan, penguin, arsenal, hiroNakamura
                )
                _recommendationsUnfiltered.postValue(recommendations)
            } catch (e: Exception) {
                _recommendationsUnfiltered.postValue(null)
            }
            finally {
                _loadingRecommendations.value = false
            }
        }
    }

    private fun getFilteredRecommendations(exclude: SuperHero?): ArrayList<SuperHero>? {
        val recommendations = _recommendationsUnfiltered.value?.clone() as ArrayList<SuperHero>?
        recommendations?.let {
            if (exclude == null)
                recommendations.removeLast()
            else
                recommendations.remove(exclude)
        }
        return recommendations
    }

    fun searchSuperHero(name: String) {
        viewModelScope.launch {
            _loadingSearchResults.value = true
            _searchError.value = false
            try {
                val heroesList = superHeroRepository.searchSuperHero(name)
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


