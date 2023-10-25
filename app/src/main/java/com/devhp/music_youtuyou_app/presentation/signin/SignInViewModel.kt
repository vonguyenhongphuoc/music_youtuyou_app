package com.devhp.music_youtuyou_app.presentation.signin

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.devhp.music_youtuyou_app.data.model.User
import com.devhp.music_youtuyou_app.domain.usecase.SignInUseCase
import com.devhp.music_youtuyou_app.presentation.MainActivity

class SignInViewModel(private val signInUseCase: SignInUseCase) : ViewModel() {

    fun signIn(user: User) = liveData {
        val isSuccess = signInUseCase.execute(user)
        emit(isSuccess)
    }

    override fun onCleared() {
        super.onCleared()
        Log.d(MainActivity.TAG, "SignInViewModel onCleared")
    }
}