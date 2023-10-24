package com.devhp.music_youtuyou_app.presentation.signup

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.devhp.music_youtuyou_app.data.model.User
import com.devhp.music_youtuyou_app.databinding.ActivitySignUpBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class SignUpActivity : AppCompatActivity() {
    @Inject
    lateinit var factory: SignUpViewModelFactory
    private lateinit var binding: ActivitySignUpBinding
    private val viewModel: SignUpViewModel by viewModels { factory }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)
        /*  val fireStoreService = FireStoreService()
          val userRemoteDataSource = UserRemoteDataSourceImpl(fireStoreService)
          val userRepository = UserRepositoryImpl(userRemoteDataSource)
          val signUpUseCase = SignUpUseCase(userRepository)
            factory = SignUpViewModelFactory(signUpUseCase)
          */

//        viewModel = ViewModelProvider(this@SignUpActivity, factory)[SignUpViewModel::class.java]
        binding.apply {
            btnRegister.setOnClickListener {
                val username = etUsername.text.toString().trim()
                val password = etPassword.text.toString().trim()
                val user = User(username, password)
                val isSuccess = viewModel.signUp(user)
                isSuccess.observe(this@SignUpActivity) {
                    if (it) {
                        Toast.makeText(
                            this@SignUpActivity,
                            "Đăng ký thành công",
                            Toast.LENGTH_SHORT
                        ).show()
                    } else {
                        Toast.makeText(this@SignUpActivity, "Đăng ký thất bại", Toast.LENGTH_SHORT)
                            .show()
                    }

                    etUsername.setText("")
                    etPassword.setText("")
                }
            }
        }
    }
}