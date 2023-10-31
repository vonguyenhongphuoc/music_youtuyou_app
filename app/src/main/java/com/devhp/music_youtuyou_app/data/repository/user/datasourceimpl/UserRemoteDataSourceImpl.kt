package com.devhp.music_youtuyou_app.data.repository.user.datasourceimpl

import com.devhp.music_youtuyou_app.data.api.FireStoreService
import com.devhp.music_youtuyou_app.data.model.User
import com.devhp.music_youtuyou_app.data.repository.user.datasource.UserRemoteDataSource

class UserRemoteDataSourceImpl(private val firestoreService: FireStoreService) :
    UserRemoteDataSource {
    override suspend fun signIn(user: User): Boolean = firestoreService.signIn(user)
    override suspend fun signUp(user: User): Boolean = firestoreService.signUp(user)
    override suspend fun checkUserExist(user: User): Boolean = firestoreService.checkUserExist(user)

}