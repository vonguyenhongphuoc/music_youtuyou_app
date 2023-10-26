package com.devhp.music_youtuyou_app.presentation.di.main

import androidx.lifecycle.SavedStateHandle
import com.devhp.music_youtuyou_app.presentation.main.MainViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class MainModule {
    @Provides
    @AppScope
    fun provideMainViewModelFactory(): MainViewModel {
        return MainViewModel()
    }
}