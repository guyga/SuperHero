package com.example.android.superhero.ui.search

import androidx.lifecycle.*
import com.example.android.superhero.domain.model.SuperHero
import com.example.android.superhero.repository.SuperHeroRepository
import kotlinx.coroutines.launch

class SearchViewModel(private val superHeroRepository: SuperHeroRepository) : ViewModel() {

    /**
     * LiveData for publishing the search results
     */
    private val _searchResults = MutableLiveData<List<SuperHero>?>()
    val searchResults: LiveData<List<SuperHero>?>
        get() = _searchResults

    /**
     * LiveData indicating the search state
     */
    private val _loadingSearchResults = MutableLiveData<Boolean>()
    val loadingSearchResults: LiveData<Boolean>
        get() = _loadingSearchResults

    /**
     * LiveData for search error indication
     */
    private val _searchError = MutableLiveData<Boolean>()
    val searchError: LiveData<Boolean>
        get() = _searchError

    /**
     * LiveData for holding the entire recommendations options - 4 recommendations
     */
    private val _recommendationsUnfiltered = MutableLiveData<ArrayList<SuperHero>?>()

    /**
     * LiveData for publishing the desired recommendations - 3 recommendations.
     * This is a [MediatorLiveData] which his sources are:
     * 1. Launch - no search results yet, filter a random recommendation
     * 2. Search results received, go through the results and filter if necessary
     *
     * More on that at the [getFilteredRecommendations] doc and at the init block
     */
    private val _recommendationsFiltered = MediatorLiveData<ArrayList<SuperHero>?>()
    val recommendationsFiltered: LiveData<ArrayList<SuperHero>?>
        get() = _recommendationsFiltered

    /**
     * LiveData indicating the fetching of the recommendations state
     */
    private val _loadingRecommendations = MutableLiveData<Boolean>()
    val loadingRecommendations: LiveData<Boolean>
        get() = _loadingRecommendations

    /**
     * Navigation LiveData for viewing the SuperHero's details
     */
    private val _navigateToDetails = MutableLiveData<SuperHero?>()
    val navigateToDetails: LiveData<SuperHero?>
        get() = _navigateToDetails

    init {
        /*
        1st recommendation source, initialization -
        - get filtered recommendation while excluding no SuperHero
         */
        _recommendationsFiltered.addSource(_recommendationsUnfiltered) {
            _recommendationsFiltered.value = getFilteredRecommendations(null)
        }

        /*
        2nd recommendation source, search results are available -
        - look through the recommendations and results to find if there is duplication to exclude from the recommendations
         */
        _recommendationsFiltered.addSource(_searchResults) { results ->
            results?.let {
                var recommendations = _recommendationsUnfiltered.value
                if (recommendations != null) {
                    var contains = false
                    for (superHero in recommendations!!) {
                        if (it.contains(superHero)) {
                            contains = true
                            recommendations = getFilteredRecommendations(superHero)
                            break
                        }
                    }
                    if (!contains)
                        recommendations = getFilteredRecommendations(null)
                }
                _recommendationsFiltered.value = recommendations
            }
        }

        fetchRecommendations()
    }

    /**
     * Retrieves through [SuperHeroRepository.getSuperHero] constant 4 recommendations
     */
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
            } finally {
                _loadingRecommendations.value = false
            }
        }
    }

    /**
     * [_recommendationsUnfiltered] holds 4 constant recommendations.
     * In order to avoid duplication - recommending a SuperHero that is already shown at the search results,
     * we either remove that duplication from the recommendation, or we randomly remove the last one when there is no duplication
     */
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

    /**
     * Search SuperHero by name - triggers the [SuperHeroRepository.searchSuperHero]
     * to retrieve the data
     */
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

    /**
     * Navigate to SuperHero details screen
     */
    fun navigateToDetails(superHero: SuperHero) {
        _navigateToDetails.value = superHero
    }

    /**
     * details Navigation completion
     */
    fun onNavigateToDetailsComplete() {
        _navigateToDetails.value = null
    }

}


