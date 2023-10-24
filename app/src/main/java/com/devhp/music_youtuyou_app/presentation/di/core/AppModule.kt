package com.devhp.music_youtuyou_app.presentation.di.core

import android.content.Context
import com.devhp.music_youtuyou_app.presentation.di.signup.SignUpSubComponent
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(subcomponents = [SignUpSubComponent::class])
class AppModule(private val context: Context) {
    @Singleton
    @Provides
    fun provideApplicationContext(): Context {
        return context.applicationContext
    }
}