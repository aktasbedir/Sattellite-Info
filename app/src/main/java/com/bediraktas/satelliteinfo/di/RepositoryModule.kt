package com.bediraktas.satelliteinfo.di

import com.bediraktas.satelliteinfo.data.repository.SatelliteRepositoryImpl
import com.bediraktas.satelliteinfo.domain.repository.SatelliteRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindSatelliteRepository(repository: SatelliteRepositoryImpl): SatelliteRepository
}