package com.example.android.superhero.ui.search

import android.view.View
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.android.superhero.domain.model.SuperHero
import com.example.android.superhero.domain.model.SuperHeroRecommendation

@BindingAdapter("searchResults")
fun bindSearchResults(recyclerView: RecyclerView, results: List<SuperHero>?) {
    (recyclerView.adapter as SearchAdapter).submitList(results)
}

@BindingAdapter("searchResultEmpty")
fun bindSearchResultsEmpty(textView: TextView, results: List<SuperHero>?) {
    if (results != null && results.isEmpty())
        textView.visibility = View.VISIBLE
    else
        textView.visibility = View.GONE
}

@BindingAdapter("searchResultError")
fun bindSearchError(textView: TextView, error: Boolean) {
    if (error)
        textView.visibility = View.VISIBLE
    else
        textView.visibility = View.GONE
}

@BindingAdapter("recommendations")
fun bindRecommendations(
    recyclerView: RecyclerView,
    recommendations: List<SuperHeroRecommendation>?
) {
    (recyclerView.adapter as RecommendationAdapter).submitList(recommendations)
}