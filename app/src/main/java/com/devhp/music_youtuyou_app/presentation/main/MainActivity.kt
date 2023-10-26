package com.devhp.music_youtuyou_app.presentation.main

import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts.*
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.devhp.music_youtuyou_app.databinding.ActivityMainBinding
import com.devhp.music_youtuyou_app.presentation.SettingsActivity
import com.devhp.music_youtuyou_app.presentation.authentication.AuthenticationActivity
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    @Inject
    lateinit var factory: MainViewModelFactory
    private val viewModel: MainViewModel by viewModels { factory }

    private lateinit var binding: ActivityMainBinding
    private val db = Firebase.firestore

    companion object {
        const val TAG = "MyTag"
    }

    @RequiresApi(Build.VERSION_CODES.Q)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel.value = 79
        binding.apply {

            btnSettings.setOnClickListener {
                startActivity(Intent(this@MainActivity, SettingsActivity::class.java))
            }

            btnAuthenticationActivity.setOnClickListener {
                startActivity(Intent(this@MainActivity, AuthenticationActivity::class.java))
            }

        }

    }

}