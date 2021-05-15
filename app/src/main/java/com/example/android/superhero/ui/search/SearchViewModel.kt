package com.example.android.superhero.ui.search

import androidx.lifecycle.*
import com.example.android.superhero.domain.model.SuperHero
import com.example.android.superhero.domain.model.SuperHeroRecommendation
import com.example.android.superhero.domain.model.toRecommendationSuperHero
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

    private val _recommendations = MediatorLiveData<ArrayList<SuperHeroRecommendation>>()
    val recommendations: LiveData<ArrayList<SuperHeroRecommendation>>
        get() = _recommendations
    private val _initRecommendations = MutableLiveData<Boolean>()

    init {

        _recommendations.addSource(_initRecommendations) {
            _recommendations.value = generateRecommendations(null)
        }
        _recommendations.addSource(_searchResults) { results ->
            results?.let {
                var currentRecommendation = _recommendations.value!!
                for (superHero in it) {
                    val asRecommendation = superHero.toRecommendationSuperHero()
                    if (currentRecommendation.contains(asRecommendation)){
                        currentRecommendation = generateRecommendations(asRecommendation)
                        break
                    }
                }
                _recommendations.value = currentRecommendation
            }
        }

        _initRecommendations.value = true
    }

    private fun generateRecommendations(exclude: SuperHeroRecommendation?): ArrayList<SuperHeroRecommendation> {
        val recommendations = arrayListOf<SuperHeroRecommendation>(
            SuperHeroRecommendation("246", "Etrigan"),
            SuperHeroRecommendation("514", "Penguin"),
            SuperHeroRecommendation("46", "Arsenal"),
            SuperHeroRecommendation("326", "Hiro Nakamura")
        )

        if(exclude == null)
            recommendations.removeLast()
        else
            recommendations.remove(exclude)
        return recommendations
    }

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


