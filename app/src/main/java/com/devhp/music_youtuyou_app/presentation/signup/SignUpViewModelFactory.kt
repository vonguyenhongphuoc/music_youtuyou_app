package com.devhp.music_youtuyou_app.presentation.signup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.devhp.music_youtuyou_app.domain.usecase.CheckUserExistUseCase
import com.devhp.music_youtuyou_app.domain.usecase.SignUpUseCase

class SignUpViewModelFactory(
    private val signUpUseCase: SignUpUseCase,
    private val checkUserExistUseCase: CheckUserExistUseCase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return SignUpViewModel(signUpUseCase, checkUserExistUseCase) as T
    }
}