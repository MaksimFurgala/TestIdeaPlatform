package com.example.testideaplatform.di

import com.example.testideaplatform.domain.repository.AppRepository
import com.example.testideaplatform.domain.usecases.DeleteItemUseCase
import com.example.testideaplatform.domain.usecases.GetItemsUseCase
import com.example.testideaplatform.domain.usecases.UpdateItemUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DomainModule {

    @Provides
    @Singleton
    fun provideGetItemsUseCase(repository: AppRepository): GetItemsUseCase {
        return GetItemsUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideUpdateItemUseCase(repository: AppRepository): UpdateItemUseCase {
        return UpdateItemUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideDeleteItemUseCase(repository: AppRepository): DeleteItemUseCase {
        return DeleteItemUseCase(repository)
    }
}