package com.devhp.music_youtuyou_app.presentation.di.signup

import com.devhp.music_youtuyou_app.domain.usecase.SignUpUseCase
import com.devhp.music_youtuyou_app.presentation.signup.SignUpViewModelFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.scopes.ActivityScoped

@Module
@InstallIn(ActivityComponent::class)
class SignUpModule {
    @ActivityScoped
    @Provides
    fun provideSignUpViewModelFactory(
        signUpUseCase: SignUpUseCase,
    ): SignUpViewModelFactory {
        return SignUpViewModelFactory(
            signUpUseCase
        )
    }
}