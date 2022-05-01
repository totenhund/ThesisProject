package com.example.thesisproject.domain.interactors

import android.content.Intent
import com.example.thesisproject.R
import com.firebase.ui.auth.AuthUI
import com.google.firebase.auth.FirebaseAuth
import javax.inject.Inject

class AuthInteractor @Inject constructor(private val auth: FirebaseAuth) {

    fun isAuthenticated(): Boolean = auth.currentUser != null


    fun buildSignIntent(): Intent {
        val providers = arrayListOf(
            AuthUI.IdpConfig.EmailBuilder().build(),
            AuthUI.IdpConfig.GoogleBuilder().build())

        // Create sign-in intent
        return AuthUI.getInstance()
            .createSignInIntentBuilder()
            .setAvailableProviders(providers)
            .setIsSmartLockEnabled(false)
            .setLogo(R.drawable.ic_launcher_foreground)
            .build()
    }

}