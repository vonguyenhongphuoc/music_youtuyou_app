package com.devhp.music_youtuyou_app.presentation.di.signin

import com.devhp.music_youtuyou_app.domain.usecase.SignInUseCase
import com.devhp.music_youtuyou_app.presentation.signin.SignInViewModelFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.scopes.ActivityScoped


@Module
@InstallIn(ActivityComponent::class)
class SignInModule {
    @ActivityScoped
    @Provides
    fun provideSignInViewModelFactory(signInUseCase: SignInUseCase): SignInViewModelFactory {
        return SignInViewModelFactory(signInUseCase)
    }
}