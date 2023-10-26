package com.devhp.music_youtuyou_app.presentation.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import com.devhp.music_youtuyou_app.R
import com.devhp.music_youtuyou_app.databinding.ActivityHomeBinding
import com.devhp.music_youtuyou_app.presentation.signin.SignInViewModel
import com.devhp.music_youtuyou_app.presentation.signin.SignInViewModelFactory
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {
    @Inject
    lateinit var factorySignIn: SignInViewModelFactory
    private val viewModelSignIn: SignInViewModel by viewModels { factorySignIn }
    private lateinit var binding: ActivityHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.textView.text = viewModelSignIn.initValue.toString()

    }
}