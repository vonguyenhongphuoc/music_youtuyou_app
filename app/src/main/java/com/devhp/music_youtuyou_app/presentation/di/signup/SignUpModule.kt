package com.devhp.music_youtuyou_app.presentation.di.signup

import com.devhp.music_youtuyou_app.domain.usecase.SignUpUseCase
import com.devhp.music_youtuyou_app.presentation.signup.SignUpViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class SignUpModule {
    @SignUpScope
    @Provides
    fun provideSignUpViewModelFactory(
        signUpUseCase: SignUpUseCase,
    ): SignUpViewModelFactory {
        return SignUpViewModelFactory(
            signUpUseCase
        )
    }
}