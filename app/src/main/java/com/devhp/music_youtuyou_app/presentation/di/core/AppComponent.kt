package com.devhp.music_youtuyou_app.presentation.di.core

import com.devhp.music_youtuyou_app.presentation.di.signup.SignUpSubComponent
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppModule::class,
        NetModule::class,
        UseCaseModule::class,
        RepositoryModule::class,
        RemoteDataModule::class
    ]
)
interface AppComponent {
    fun signUpComponent(): SignUpSubComponent.Factory
}