package com.devhp.music_youtuyou_app.presentation.signin

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.devhp.music_youtuyou_app.domain.usecase.SignInUseCase

class SignInViewModelFactory(private val signInUseCase: SignInUseCase) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return SignInViewModel(signInUseCase) as T
    }
}