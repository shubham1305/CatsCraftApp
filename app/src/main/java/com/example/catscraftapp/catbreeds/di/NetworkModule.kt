package com.example.catscraftapp.catbreeds.di

import com.example.catscraftapp.catbreeds.data.remote.api.CatsRequestAPI
import com.example.catscraftapp.common.AppConstants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    @Singleton
    fun providesRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(AppConstants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun providesCatsRequestApi(retrofit: Retrofit): CatsRequestAPI {
        return retrofit.create(CatsRequestAPI::class.java)
    }
}