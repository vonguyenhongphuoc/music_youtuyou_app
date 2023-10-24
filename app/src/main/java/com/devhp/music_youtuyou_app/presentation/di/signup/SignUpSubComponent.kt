package com.devhp.music_youtuyou_app.presentation.di.signup

import com.devhp.music_youtuyou_app.presentation.signup.SignUpActivity
import dagger.Subcomponent

@SignUpScope
@Subcomponent(modules = [SignUpModule::class])
interface SignUpSubComponent {
    fun inject(signUpActivity: SignUpActivity)

    @Subcomponent.Factory
    interface Factory {
        fun create(): SignUpSubComponent
    }
}