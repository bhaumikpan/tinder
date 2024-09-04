package com.example.feature_data.di

import com.example.feature_domain.repository.FeatureRepo
import com.example.feature_data.repo.FeatureRepoImpl

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class FeatureRepoModule {
    @Binds
    abstract fun bindFeatureRepository(impl: FeatureRepoImpl): FeatureRepo
}