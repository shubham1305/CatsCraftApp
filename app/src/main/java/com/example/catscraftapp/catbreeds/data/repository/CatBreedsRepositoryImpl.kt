package com.example.catscraftapp.catbreeds.data.repository

import com.example.catscraftapp.catbreeds.api.CatBreedsRequestAPI
import com.example.catscraftapp.common.AppConstants
import com.example.catscraftapp.common.helperclasses.NetworkResponse
import com.example.catscraftapp.catbreeds.domain.model.CatBreed
import com.example.catscraftapp.catbreeds.domain.repository.CatBreedsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class CatBreedsRepositoryImpl @Inject constructor(
    private val catBreedsRequestAPI: CatBreedsRequestAPI
): CatBreedsRepository {
    override fun getCatBreedsList(
        page: Int,
        pageSize: Int
    ): Flow<NetworkResponse<List<CatBreed>>> = flow {
        try {
            emit(NetworkResponse.Loading())
            val response = catBreedsRequestAPI.getCatBreedsList(page, pageSize)
            emit(NetworkResponse.Success(response))
        } catch (e: HttpException) {
            emit(NetworkResponse.Error(e.localizedMessage ?: AppConstants.ERROR_HTTP_EXCEPTION))
        } catch (e: IOException) {
            emit(NetworkResponse.Error(AppConstants.ERROR_IO_EXCEPTION))
        }
    }
}