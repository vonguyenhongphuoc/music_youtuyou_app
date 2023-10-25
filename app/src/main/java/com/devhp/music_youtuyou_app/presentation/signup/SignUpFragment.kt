package com.devhp.music_youtuyou_app.presentation.signup

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.devhp.music_youtuyou_app.data.model.User
import com.devhp.music_youtuyou_app.databinding.FragmentSignUpBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class SignUpFragment : Fragment() {
    @Inject
    lateinit var factory: SignUpViewModelFactory
    private lateinit var binding: FragmentSignUpBinding
    private val viewModel: SignUpViewModel by viewModels { factory }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentSignUpBinding.inflate(inflater, container, false)
        binding.btnRegister.setOnClickListener {
            signUp()
        }
        return binding.root
    }

    private fun signUp() {
        val username = binding.etUsername.text.toString().trim()
        val password = binding.etPassword.text.toString().trim()
        if (username.isNotEmpty() && password.isNotEmpty()) {
            val user = User(username, password)
            val isSuccess = viewModel.signUp(user)
            isSuccess.observe(requireActivity()) {
                if (it) {
                    Toast.makeText(
                        requireActivity(),
                        "Đăng ký thành công",
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    Toast.makeText(requireActivity(), "Đăng ký thất bại", Toast.LENGTH_SHORT)
                        .show()
                }

                binding.etUsername.setText("")
                binding.etPassword.setText("")
            }
        } else {
            Toast.makeText(requireActivity(), "Vui lòng nhập đầy đủ thông tin!", Toast.LENGTH_SHORT)
                .show()
        }

    }

}