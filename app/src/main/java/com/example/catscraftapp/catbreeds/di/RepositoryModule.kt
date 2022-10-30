package com.example.catscraftapp.catbreeds.di

import com.example.catscraftapp.catbreeds.api.CatBreedsRequestAPI
import com.example.catscraftapp.catbreeds.data.repository.CatBreedsRepositoryImpl
import com.example.catscraftapp.catbreeds.domain.repository.CatBreedsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Provides
    @Singleton
    fun providesCatBreedsRepository(
        catBreedsRequestAPI: CatBreedsRequestAPI
    ): CatBreedsRepository = CatBreedsRepositoryImpl(catBreedsRequestAPI)
}