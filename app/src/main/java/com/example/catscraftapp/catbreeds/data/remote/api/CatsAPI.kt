package com.example.catscraftapp.catbreeds.data.remote.api

import com.example.catscraftapp.catbreeds.data.remote.dto.CatBreedDto
import com.example.catscraftapp.common.AppConstants
import retrofit2.http.GET
import retrofit2.http.Query

interface CatsRequestAPI {

    @GET(AppConstants.ENDPOINT_GET_CAT_BREEDS)
    suspend fun getCatBreedsList(
        @Query(AppConstants.PARAM_PAGE_NUMBER) page: Int,
        @Query(AppConstants.PARAM_PAGE_LIMIT) pageSize: Int
    ): List<CatBreedDto>
}