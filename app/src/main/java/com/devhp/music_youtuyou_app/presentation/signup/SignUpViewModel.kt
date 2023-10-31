package com.devhp.music_youtuyou_app.presentation.signup

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.devhp.music_youtuyou_app.data.model.User
import com.devhp.music_youtuyou_app.domain.usecase.CheckUserExistUseCase
import com.devhp.music_youtuyou_app.domain.usecase.SignUpUseCase
import com.devhp.music_youtuyou_app.presentation.main.MainActivity

class SignUpViewModel(
    private val signUpUseCase: SignUpUseCase,
    private val checkUserExistUseCase: CheckUserExistUseCase
) : ViewModel() {

    fun signUp(user: User) = liveData {
        val isUserExist = checkUserExistUseCase.execute(user)
        if (!isUserExist) {
            val isSuccess = signUpUseCase.execute(user)
            emit(isSuccess)
        } else {
            emit(false)
        }

    }

    init {
        Log.d(MainActivity.TAG, "SignUpViewModel init")
    }

    override fun onCleared() {
        super.onCleared()
        Log.d(MainActivity.TAG, "SignUpViewModel onCleared")
    }
}