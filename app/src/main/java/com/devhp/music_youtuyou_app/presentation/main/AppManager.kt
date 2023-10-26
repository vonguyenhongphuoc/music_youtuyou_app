package com.devhp.music_youtuyou_app.presentation.main

import android.util.Log
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AppManager @Inject constructor() {
    var testValue = 0

    init {
        Log.d(MainActivity.TAG, "Init AppManager")

    }
}