package com.devhp.music_youtuyou_app.presentation

import android.app.Application
import com.devhp.music_youtuyou_app.presentation.di.Injector
import com.devhp.music_youtuyou_app.presentation.di.core.AppComponent
import com.devhp.music_youtuyou_app.presentation.di.core.AppModule
import com.devhp.music_youtuyou_app.presentation.di.core.NetModule
import com.devhp.music_youtuyou_app.presentation.di.core.RemoteDataModule
import com.devhp.music_youtuyou_app.presentation.di.signup.SignUpSubComponent

class App : Application(), Injector {
    private lateinit var appComponent: AppComponent
    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder().appModule(AppModule(applicationContext))
            .netModule(NetModule())
            .remoteDataModule(RemoteDataModule())
            .build()
    }

    override fun createSignUpSubComponent(): SignUpSubComponent {
        return appComponent.signUpComponent().create()

    }
}