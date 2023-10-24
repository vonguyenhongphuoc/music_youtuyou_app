package com.devhp.music_youtuyou_app.presentation.di.core

import com.devhp.music_youtuyou_app.data.api.FireStoreService
import com.devhp.music_youtuyou_app.data.repository.user.datasource.UserRemoteDataSource
import com.devhp.music_youtuyou_app.data.repository.user.datasourceimpl.UserRemoteDataSourceImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RemoteDataModule {

    @Singleton
    @Provides
    fun provideUseRemoteDataSource(fireStoreService: FireStoreService): UserRemoteDataSource {
        return UserRemoteDataSourceImpl(fireStoreService)
    }

}