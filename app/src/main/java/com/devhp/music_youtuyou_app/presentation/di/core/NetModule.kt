package com.devhp.music_youtuyou_app.presentation.di.core

import com.devhp.music_youtuyou_app.data.api.FireStoreService
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class NetModule {
    @Singleton
    @Provides
    fun provideFireStoreService(): FireStoreService {
        return FireStoreService()
    }
}