package com.example.autocrypt.di

import com.example.autocrypt.data.repository.CenterRepository
import com.example.autocrypt.data.repository.CenterRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryBindModule {

    @Binds
    abstract fun bindCenterRepository(
        centerRepositoryImpl: CenterRepositoryImpl
    ): CenterRepository


}
