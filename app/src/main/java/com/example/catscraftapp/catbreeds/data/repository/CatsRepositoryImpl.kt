package com.example.catscraftapp.catbreeds.data.repository

import com.example.catscraftapp.catbreeds.data.remote.api.CatsRequestAPI
import com.example.catscraftapp.catbreeds.data.remote.dto.toCatBreed
import com.example.catscraftapp.common.AppConstants
import com.example.catscraftapp.common.helperclasses.NetworkResponse
import com.example.catscraftapp.catbreeds.domain.model.CatBreed
import com.example.catscraftapp.catbreeds.domain.repository.CatsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class CatsRepositoryImpl @Inject constructor(
    private val catsRequestAPI: CatsRequestAPI
): CatsRepository {
    override fun getCatBreedsList(
        page: Int,
        pageSize: Int
    ): Flow<NetworkResponse<List<CatBreed>>> = flow {
        try {
            emit(NetworkResponse.Loading())
            val response = catsRequestAPI.getCatBreedsList(page, pageSize)
            emit(NetworkResponse.Success(response.map { it.toCatBreed() }))
        } catch (e: HttpException) {
            emit(NetworkResponse.Error(e.localizedMessage ?: AppConstants.ERROR_HTTP_EXCEPTION))
        } catch (e: IOException) {
            emit(NetworkResponse.Error(AppConstants.ERROR_IO_EXCEPTION))
        }
    }
}