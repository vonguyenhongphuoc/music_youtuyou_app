package com.devhp.music_youtuyou_app.presentation.signin

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.devhp.music_youtuyou_app.data.model.User
import com.devhp.music_youtuyou_app.domain.usecase.SignInUseCase
import com.devhp.music_youtuyou_app.presentation.main.MainActivity
import kotlin.random.Random
import kotlin.random.nextInt

class SignInViewModel(private val signInUseCase: SignInUseCase) : ViewModel() {
    var initValue = 0
    fun signIn(user: User) = liveData {
        val isSuccess = signInUseCase.execute(user)
        emit(isSuccess)
    }


    private fun getInt(): Int {
        return Random.nextInt(0..1000000)
    }

    init {
        initValue = getInt()
        Log.d(MainActivity.TAG, "SignInViewModel init")
    }

    override fun onCleared() {
        super.onCleared()
        Log.d(MainActivity.TAG, "SignInViewModel onCleared")
    }
}