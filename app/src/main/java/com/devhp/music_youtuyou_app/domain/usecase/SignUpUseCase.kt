package com.devhp.music_youtuyou_app.domain.usecase

import com.devhp.music_youtuyou_app.data.model.User
import com.devhp.music_youtuyou_app.domain.repository.UserRepository

class SignUpUseCase(private val userRepository: UserRepository) {
    suspend fun execute(user: User): Boolean = userRepository.signUp(user)
}