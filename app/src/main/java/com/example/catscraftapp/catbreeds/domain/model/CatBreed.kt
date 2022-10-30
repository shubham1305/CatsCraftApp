package com.example.catscraftapp.catbreeds.domain.model


import com.google.gson.annotations.SerializedName

data class CatBreed(
    val id: String,
    val description: String,
    val image: CatImage?,
    val name: String,
    val origin: String,
    val weight: CatWeight,
    @SerializedName("life_span")
    val lifeSpan: String,
    @SerializedName("alt_names")
    val altNames: String?,
    @SerializedName("wikipedia_url")
    val wikipediaUrl: String
)