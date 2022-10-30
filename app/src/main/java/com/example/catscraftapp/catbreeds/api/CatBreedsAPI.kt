package com.example.catscraftapp.catbreeds.api

import com.example.catscraftapp.catbreeds.domain.model.CatBreed
import retrofit2.http.GET
import retrofit2.http.Query

interface CatBreedsRequestAPI {

    @GET("v1/breeds/")
    suspend fun getCatBreedsList(
        @Query("page") page: Int,
        @Query("limit") pageSize: Int
    ): List<CatBreed>
}