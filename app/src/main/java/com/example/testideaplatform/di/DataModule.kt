package com.example.testideaplatform.di

import android.content.Context
import com.example.testideaplatform.data.db.AppDao
import com.example.testideaplatform.data.mapper.DatabaseMapper
import com.example.testideaplatform.data.repository.AppRepositoryImpl
import com.example.testideaplatform.domain.repository.AppRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import org.mapstruct.factory.Mappers
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataModule {

    @Provides
    @Singleton
    fun provideAppRepository(
        appDao: AppDao,
        mapper: DatabaseMapper
    ): AppRepository {
        return AppRepositoryImpl(appDao, mapper)
    }

    @Provides
    @Singleton
    fun provideDatabaseMapper(): DatabaseMapper {
        return Mappers.getMapper(DatabaseMapper::class.java)
    }
}