package com.example.evaluacionbanregio.di

import com.example.evaluacionbanregio.data.CardDataSource
import com.example.evaluacionbanregio.data.CardRemoteSource
import com.example.evaluacionbanregio.data.MovementDataSource
import com.example.evaluacionbanregio.data.MovementsRemoteSource
import com.example.evaluacionbanregio.data.apis.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AppModule {

    @Provides
    @Singleton
    fun provideMovementsDataSource(apiService: ApiService):
            MovementDataSource = MovementsRemoteSource(apiService)

    @Provides
    @Singleton
    fun provideCardDataSource(apiService: ApiService):
            CardDataSource = CardRemoteSource(apiService)
}