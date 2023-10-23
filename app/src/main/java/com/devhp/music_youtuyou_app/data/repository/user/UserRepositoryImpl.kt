package com.devhp.music_youtuyou_app.data.repository.user

import com.devhp.music_youtuyou_app.data.model.User
import com.devhp.music_youtuyou_app.data.repository.user.datasource.UserRemoteDataSource
import com.devhp.music_youtuyou_app.domain.repository.UserRepository

class UserRepositoryImpl(private val userRemoteDataSource: UserRemoteDataSource) : UserRepository {
    override suspend fun signIn(user: User): Boolean {
        return userRemoteDataSource.signIn(user)
    }

    override suspend fun signUp(user: User): Boolean {
        return userRemoteDataSource.signUp(user)
    }
}