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

    suspend fun signIn(user: User): Boolean = withContext(Dispatchers.IO) {
        try {
            val result = db.collection("users")
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
            Log.e(MainActivity.TAG, "Error sign up", e)
            return@withContext false
        }

    }


    suspend fun signUp(user: User): Boolean = withContext(Dispatchers.IO) {
        Log.d(MainActivity.TAG, "Sign Up handling in ${Thread.currentThread().name}")
        val userHashMap = hashMapOf(
            "username" to user.username,
            "password" to user.password
        )

        try {
            val result = signIn(user)

            if (result) {
                return@withContext false
            } else {
                val documentReference = db.collection("users")
                    .add(userHashMap)
                    .await()

                val documentId = documentReference.id
                Log.d(MainActivity.TAG, "DocumentSnapshot added with ID: $documentId")
                return@withContext true
            }
        } catch (e: Exception) {
            // Xử lý lỗi Firebase tại đây
            Log.e(MainActivity.TAG, "Error sign up", e)
            return@withContext false
        }
    }
}