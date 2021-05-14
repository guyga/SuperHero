package com.example.android.superhero.network.responses

import com.google.gson.annotations.SerializedName

class SuperHeroSearchResponse(
    var response: String,
    @SerializedName("results-for")
    var resultsFor: String,
    var results: List<SuperHeroNetworkEntity>?
)