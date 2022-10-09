package com.example.anotacoes.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.anotacoes.R
import com.example.anotacoes.fragments.SignInFragment

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        supportFragmentManager.beginTransaction().replace(R.id.fragmentContainerView, SignInFragment()).commit()

    }
}