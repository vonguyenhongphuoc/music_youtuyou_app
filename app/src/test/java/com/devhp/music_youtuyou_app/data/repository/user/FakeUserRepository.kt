package com.devhp.music_youtuyou_app.data.repository.user

import com.devhp.music_youtuyou_app.data.model.User
import com.devhp.music_youtuyou_app.domain.repository.UserRepository
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine


class FakeUserRepository : UserRepository {
    private val users = mutableListOf<User>()

    init {
        users.add(User("hatachi", "123456"))
        users.add(User("sasuke", "P@ssw0rd"))
    }

    override suspend fun signIn(user: User): Boolean = suspendCoroutine { continuation ->
        for (currentUser in users) {
            if (currentUser.username == user.username && currentUser.password == user.password) {
                continuation.resume(true)
                return@suspendCoroutine
            }
        }
        continuation.resume(false)
        return@suspendCoroutine
    }

    override suspend fun signUp(user: User): Boolean = suspendCoroutine { continuation ->
        for (currentUser in users) {
            if (currentUser.username == user.username) {
                continuation.resume(false)
                return@suspendCoroutine

            }
        }
        continuation.resume(true)
        return@suspendCoroutine
    }
}