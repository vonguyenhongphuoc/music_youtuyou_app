package com.devhp.music_youtuyou_app.presentation.main

import android.util.Log
import androidx.lifecycle.ViewModel


class MainViewModel :
    ViewModel() {

    var value = 0

    init {
        Log.d(MainActivity.TAG, "Init ViewModel")
    }

    override fun onCleared() {
        super.onCleared()
        Log.d(MainActivity.TAG, "Clear MainViewModel")
    }

}