package com.example.testideaplatform.di

import android.app.Application
import com.example.testideaplatform.data.db.AppDao
import com.example.testideaplatform.data.db.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideAppDatabase(application: Application): AppDatabase {
        return AppDatabase.getInstance(application)
    }

    @Provides
    @Singleton
    fun provideDao(appDb: AppDatabase): AppDao {
        return appDb.appDao()
    }
}