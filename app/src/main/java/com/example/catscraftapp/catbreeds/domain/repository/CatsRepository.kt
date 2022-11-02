package com.example.catscraftapp.catbreeds.domain.repository

import com.example.catscraftapp.common.helperclasses.NetworkResponse
import com.example.catscraftapp.catbreeds.domain.model.CatBreed
import kotlinx.coroutines.flow.Flow

interface CatsRepository {
    fun getCatBreedsList(
        page: Int,
        pageSize: Int
    ): Flow<NetworkResponse<List<CatBreed>>>
}