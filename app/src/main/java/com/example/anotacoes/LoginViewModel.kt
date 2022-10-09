package com.example.anotacoes

import android.widget.EditText
import androidx.lifecycle.ViewModel
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class LoginViewModel: ViewModel() {
    val firebaseAuth = FirebaseAuth.getInstance()
    var firebaseUser: FirebaseUser? = null

    fun createUser(
        edtTxtEmail: EditText,
        edtTxtPassword: EditText,
        edtTxtRePassword: EditText
    ): Task<AuthResult> {
        val email = edtTxtEmail.text.toString()
        val password = edtTxtPassword.text.toString()
        val rePassword = edtTxtRePassword.text.toString()
        if (email.isNullOrEmpty()
            || password.isNullOrEmpty()
            || rePassword.isNullOrEmpty())
            throw object : Throwable() {
                override val message: String?
                    get() = "Preencha todos os campos"
            }
        if (!password.equals(rePassword))
            throw object : Throwable() {
                override val message: String?
                    get() = "Senhas não conferem."
            }
        return firebaseAuth.createUserWithEmailAndPassword(
            email, password
        )
    }

    fun signInUser(
        edtTxtEmail: EditText,
        edtTxtPassword: EditText
    ): Task<AuthResult> {
        val email = edtTxtEmail.text.toString()
        val password = edtTxtPassword.text.toString()
        if (email.isNullOrEmpty() || password.isNullOrEmpty())
            throw object : Throwable() {
                override val message: String?
                    get() = "Preencha todos os campos"
            }
        return firebaseAuth.signInWithEmailAndPassword(
            email, password
        )
    }


}