package com.devhp.music_youtuyou_app.data.repository.user.datasource

import com.devhp.music_youtuyou_app.data.model.User

interface UserRemoteDataSource {
    suspend fun signIn(user: User): Boolean

    suspend fun signUp(user: User): Boolean

    suspend fun checkUserExist(user: User): Boolean
}