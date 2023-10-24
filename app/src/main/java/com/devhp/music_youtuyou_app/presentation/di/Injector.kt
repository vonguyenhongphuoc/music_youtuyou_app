package com.devhp.music_youtuyou_app.presentation.di

import com.devhp.music_youtuyou_app.presentation.di.signup.SignUpSubComponent

interface Injector {
    fun createSignUpSubComponent():SignUpSubComponent
}