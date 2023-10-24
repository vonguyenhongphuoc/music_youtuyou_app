package com.devhp.music_youtuyou_app.presentation.signup

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.devhp.music_youtuyou_app.data.model.User
import com.devhp.music_youtuyou_app.data.repository.user.FakeUserRepository
import com.devhp.music_youtuyou_app.domain.usecase.SignUpUseCase
import com.devhp.music_youtuyou_app.getOrAwaitValue
import com.google.common.truth.Truth.assertThat


import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config

@Config(manifest= Config.NONE)
@RunWith(AndroidJUnit4::class)
class SignUpViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()
    private lateinit var viewModel: SignUpViewModel

    @Before
    fun setUp() {
        val fakeUserRepository = FakeUserRepository()
        val signUpUseCase = SignUpUseCase(fakeUserRepository)
        viewModel = SignUpViewModel(signUpUseCase)
    }

    @Test
    fun signUp_returnsCorrectResult() {
        val user = User("hohoho", "123456")
        val isSuccess = viewModel.signUp(user).getOrAwaitValue()
        assertThat(isSuccess).isEqualTo(true)

    }

}