package com.devhp.music_youtuyou_app.domain.repository

import com.devhp.music_youtuyou_app.data.model.User

interface UserRepository {
    suspend fun signIn(user: User): Boolean

    suspend fun signUp(user: User): Boolean
}