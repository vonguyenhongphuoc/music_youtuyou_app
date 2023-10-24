package com.devhp.music_youtuyou_app.presentation.signup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.devhp.music_youtuyou_app.data.model.User
import com.devhp.music_youtuyou_app.domain.usecase.SignUpUseCase
import kotlinx.coroutines.Dispatchers

class SignUpViewModel(
    private val signUpUseCase: SignUpUseCase,
) : ViewModel() {
    fun signUp(user: User) = liveData {
        val isSuccess = signUpUseCase.execute(user)
        emit(isSuccess)
    }
}