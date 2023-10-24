package com.devhp.music_youtuyou_app.presentation.di.core

import com.devhp.music_youtuyou_app.data.api.FireStoreService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetModule {
    @Singleton
    @Provides
    fun provideFireStoreService(): FireStoreService {
        return FireStoreService()
    }
}