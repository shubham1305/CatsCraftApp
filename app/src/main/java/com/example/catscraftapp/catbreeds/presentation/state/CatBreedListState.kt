package com.example.catscraftapp.catbreeds.presentation.state

import com.example.catscraftapp.catbreeds.domain.model.CatBreed

data class CatBreedListState(
    val isLoading: Boolean = false,
    val breedList: List<CatBreed> = emptyList(),
    val error: String? = null,
    val endReached: Boolean = false,
    val page: Int = 0
)