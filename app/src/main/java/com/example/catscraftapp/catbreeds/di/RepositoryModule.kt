package com.example.catscraftapp.catbreeds.di

import com.example.catscraftapp.catbreeds.data.remote.api.CatsRequestAPI
import com.example.catscraftapp.catbreeds.data.repository.CatsRepositoryImpl
import com.example.catscraftapp.catbreeds.domain.repository.CatsRepository
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
    fun providesCatsRepository(
        catsRequestAPI: CatsRequestAPI
    ): CatsRepository = CatsRepositoryImpl(catsRequestAPI)
}