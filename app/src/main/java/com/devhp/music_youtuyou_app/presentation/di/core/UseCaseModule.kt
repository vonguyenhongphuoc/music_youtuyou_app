package com.devhp.music_youtuyou_app.presentation.di.core

import com.devhp.music_youtuyou_app.domain.repository.UserRepository
import com.devhp.music_youtuyou_app.domain.usecase.SignInUseCase
import com.devhp.music_youtuyou_app.domain.usecase.SignUpUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
class UseCaseModule {

    @Provides
    fun provideSignInUseCase(userRepository: UserRepository): SignInUseCase {
        return SignInUseCase(userRepository)
    }

    @Provides
    fun provideSignUpUseCase(userRepository: UserRepository): SignUpUseCase {
        return SignUpUseCase(userRepository)
    }
}