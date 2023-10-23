package com.devhp.music_youtuyou_app.data.api

import android.util.Log
import com.devhp.music_youtuyou_app.presentation.MainActivity
import com.devhp.music_youtuyou_app.data.model.User
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class FireStoreService {
    private val db = Firebase.firestore

    suspend fun signIn(user: User): Boolean = suspendCoroutine { continuation ->
        db.collection("users")
            .get().addOnSuccessListener { result ->
                for (document in result) {
                    val username = document.data["username"]
                    val password = document.data["password"]
                    Log.d(MainActivity.TAG, "${document.id} => ${document.data}")
                    if (user.username == username && user.password == password) {
                        continuation.resume(true)
                        break
                    }
                }
                continuation.resume(false)

            }.addOnFailureListener { e ->
                Log.d(MainActivity.TAG, "Error sign in", e)
                continuation.resume(false)
            }

    }

    suspend fun signUp(user: User): Boolean = suspendCoroutine { continuation ->
        val userHashMap = hashMapOf(
            "username" to user.username,
            "password" to user.password
        )
        db.collection("users")
            .add(userHashMap)
            .addOnSuccessListener { documentReference ->
                val documentId = documentReference.id
                Log.d(MainActivity.TAG, "DocumentSnapshot added with ID: $documentId")
                continuation.resume(true)
            }
            .addOnFailureListener { e ->
                Log.d(MainActivity.TAG, "Error sign up", e)
                continuation.resume(false)
            }
    }

}