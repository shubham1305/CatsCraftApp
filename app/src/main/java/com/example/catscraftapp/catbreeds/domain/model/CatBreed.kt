package com.example.catscraftapp.catbreeds.domain.model

import androidx.annotation.Keep

@Keep
data class CatBreed(
    val id: String,
    val description: String,
    val name: String,
    val origin: String,
    val lifeSpan: String,
    val altNames: String?,
    val wikipediaUrl: String?,
    val imageUrl: String?,
    val weight: String
)