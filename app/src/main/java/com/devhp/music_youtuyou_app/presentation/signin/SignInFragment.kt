package com.devhp.music_youtuyou_app.presentation.signin

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.devhp.music_youtuyou_app.R
import com.devhp.music_youtuyou_app.data.model.User
import com.devhp.music_youtuyou_app.databinding.FragmentSignInBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class SignInFragment : Fragment() {

    @Inject
    lateinit var factory: SignInViewModelFactory
    private lateinit var binding: FragmentSignInBinding
    private val viewModel: SignInViewModel by viewModels { factory }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentSignInBinding.inflate(inflater, container, false)
        binding.etUsername.setText("sasuke")
        binding.etPassword.setText("123456")
        binding.btnSignIn.setOnClickListener { view ->
            signIn()
        }
        return binding.root
    }

    private fun signIn() {
        val username = binding.etUsername.text.toString()
        val password = binding.etPassword.text.toString()
        if (username.isNotEmpty() && password.isNotEmpty()) {
            val user = User(username, password)
            val result = viewModel.signIn(user)
            result.observe(requireActivity()) { isSuccess ->
                if (isSuccess) {
                    Toast.makeText(
                        requireActivity(),
                        "Đăng nhập thành công",
                        Toast.LENGTH_SHORT
                    )
                        .show()
                    findNavController().navigate(R.id.action_global_homeFragment)
                } else {
                    Toast.makeText(
                        requireActivity(),
                        "Thông tin tài khoản hoặc mật khẩu không chính xác!",
                        Toast.LENGTH_SHORT
                    )
                        .show()
                }
            }
        } else {
            Toast.makeText(requireActivity(), "Vui lòng điền đầy đủ thông tin!", Toast.LENGTH_SHORT)
                .show()
        }
    }

}