package com.example.autocrypt.di

import com.example.autocrypt.data.repository.CenterRepository
import com.example.autocrypt.di.annotation.IoDispatcher
import com.example.autocrypt.domain.usecase.GetCenterListUseCaseImple
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher


@Module
@InstallIn(SingletonComponent::class)
object MapUseCaseModule {

    @Provides
    fun provideCenterListUseCase(
        centerRepositoryImple: CenterRepository,
        @IoDispatcher ioDispatcher: CoroutineDispatcher,
    ): GetCenterListUseCaseImple {
        return GetCenterListUseCaseImple(centerRepositoryImple, ioDispatcher)
    }


}