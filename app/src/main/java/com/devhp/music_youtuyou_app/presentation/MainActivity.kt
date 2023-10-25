package com.devhp.music_youtuyou_app.presentation

import android.content.Context
import android.content.Intent
import android.net.wifi.WifiManager
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts.*
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.devhp.music_youtuyou_app.databinding.ActivityMainBinding
import com.devhp.music_youtuyou_app.presentation.authentication.AuthenticationActivity
import com.devhp.music_youtuyou_app.presentation.signup.SignUpActivity
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val db = Firebase.firestore
    var wifiManager: WifiManager? = null

    companion object {
        const val TAG = "MyTag"
    }

    @RequiresApi(Build.VERSION_CODES.Q)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        wifiManager =
            applicationContext.getSystemService(Context.WIFI_SERVICE) as WifiManager?
        binding.apply {
            btnRegister.setOnClickListener {
                val username = etUsername.text.toString().trim()
                val password = etPassword.text.toString().trim()
                if (username.isNotEmpty() && password.isNotEmpty()) {
                    register(username, password)
                    clearRegisterForm()
                    return@setOnClickListener
                }
                Toast.makeText(this@MainActivity, "Please input your text", Toast.LENGTH_LONG)
                    .show()
            }
            btnGetUsers.setOnClickListener {
                getUsers()
            }

            btnSignUp.setOnClickListener {
                startActivity(Intent(this@MainActivity, SignUpActivity::class.java))
            }

            btnWifi.setOnClickListener {
                val isWifiEnabled: Boolean = wifiManager?.isWifiEnabled ?: false
                if (isWifiEnabled) {
                    btnWifi.text = "Turn on Wifi"
                    val panelIntent = Intent(Settings.Panel.ACTION_WIFI)
                    startActivityForResult(panelIntent, 1)
                } else {
                    btnWifi.text = "Turn off Wifi"
                    val panelIntent = Intent(Settings.Panel.ACTION_WIFI)
                    startActivityForResult(panelIntent, 1)
                }
            }

            btnSettings.setOnClickListener {
                startActivity(Intent(this@MainActivity, SettingsActivity::class.java))
            }

            btnAuthenticationActivity.setOnClickListener {
                startActivity(Intent(this@MainActivity, AuthenticationActivity::class.java))
            }

        }

    }

    private fun getUsers() {
        db.collection("users")
            .get().addOnSuccessListener { result ->
                for (document in result) {
                    Log.d(TAG, "${document.id} => ${document.data}")
                }
            }.addOnFailureListener { exception ->
                Toast.makeText(
                    this@MainActivity,
                    exception.message.toString(),
                    Toast.LENGTH_LONG
                ).show()
            }
    }

    private fun clearRegisterForm() {
        binding.etUsername.setText("")
        binding.etPassword.setText("")
    }

    private fun register(username: String, password: String) {
        // Create a new user with a first and last name
        val user = hashMapOf(
            "username" to username,
            "password" to password
        )
        // Add a new document with a generated ID
        db.collection("users")
            .add(user)
            .addOnSuccessListener { documentReference ->
                Log.d(TAG, "DocumentSnapshot added with ID: ${documentReference.id}")
                Toast.makeText(this@MainActivity, "Register successfully", Toast.LENGTH_LONG).show()
            }
            .addOnFailureListener { e ->
                Log.w(TAG, "Error adding document", e)
                Toast.makeText(this@MainActivity, "Register failed", Toast.LENGTH_LONG).show()
            }
    }
}