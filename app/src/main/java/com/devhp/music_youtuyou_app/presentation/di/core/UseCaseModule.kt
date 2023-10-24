package com.devhp.music_youtuyou_app.presentation.di.core

import com.devhp.music_youtuyou_app.domain.repository.UserRepository
import com.devhp.music_youtuyou_app.domain.usecase.SignInUseCase
import com.devhp.music_youtuyou_app.domain.usecase.SignUpUseCase
import dagger.Provides

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