package com.devhp.music_youtuyou_app.data.api

import android.util.Log
import com.devhp.music_youtuyou_app.data.model.User
import com.devhp.music_youtuyou_app.presentation.main.MainActivity
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

class FireStoreService {
    private val db = Firebase.firestore


    companion object {
        /*Collection Paths*/
        private const val USERS_COLLECTION = "users"

    }


    suspend fun signIn(user: User): Boolean = withContext(Dispatchers.IO) {
        try {

            val result = db.collection(USERS_COLLECTION)
                .get().await()
            for (document in result) {
                val username = document.data["username"]
                val password = document.data["password"]
                Log.d(MainActivity.TAG, "${document.id} => ${document.data}")
                return@withContext user.username == username && user.password == password
            }
            return@withContext false
        } catch (e: Exception) {
            // Xử lý lỗi Firebase tại đây
            Log.e(MainActivity.TAG, "Error signIn", e)
            return@withContext false
        }

    }


    suspend fun signUp(user: User): Boolean = withContext(Dispatchers.IO) {
        val userHashMap = hashMapOf(
            "username" to user.username,
            "password" to user.password
        )

        try {
            val documentReference = db.collection(USERS_COLLECTION)
                .add(userHashMap)
                .await()

            val documentId = documentReference.id
            Log.d(MainActivity.TAG, "DocumentSnapshot added with ID: $documentId")
            return@withContext true

        } catch (e: Exception) {
            // Xử lý lỗi Firebase tại đây
            Log.e(MainActivity.TAG, "Error signUp", e)
            return@withContext false
        }
    }

    suspend fun checkUserExist(user: User): Boolean = withContext(Dispatchers.IO) {
        try {
            val result = db.collection(USERS_COLLECTION).get().await()
            for (document in result) {
                val username = document.data["username"] ?: ""
                if (username == user.username) {
                    return@withContext true
                }
            }
            return@withContext false
        } catch (e: Exception) {
            Log.e(MainActivity.TAG, "Error checkUserExist")
            return@withContext false
        }
    }
}