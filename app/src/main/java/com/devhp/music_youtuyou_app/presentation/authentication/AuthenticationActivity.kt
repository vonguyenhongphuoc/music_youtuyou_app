package com.devhp.music_youtuyou_app.presentation.authentication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.devhp.music_youtuyou_app.databinding.ActivityAuthenticationBinding
import com.devhp.music_youtuyou_app.presentation.main.MainActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AuthenticationActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAuthenticationBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAuthenticationBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}