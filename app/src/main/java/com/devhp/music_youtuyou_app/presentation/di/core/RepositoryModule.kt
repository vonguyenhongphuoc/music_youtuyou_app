package com.devhp.music_youtuyou_app.presentation.di.core

import com.devhp.music_youtuyou_app.data.repository.user.UserRepositoryImpl
import com.devhp.music_youtuyou_app.data.repository.user.datasource.UserRemoteDataSource
import com.devhp.music_youtuyou_app.domain.repository.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {
    @Provides
    @Singleton
    fun provideUserRepository(userRemoteDataSource: UserRemoteDataSource): UserRepository {
        return UserRepositoryImpl(userRemoteDataSource)
    }
}