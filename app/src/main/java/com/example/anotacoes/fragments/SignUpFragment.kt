package com.example.anotacoes.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.anotacoes.LoginViewModel
import com.example.anotacoes.R
class SignUpFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view =  inflater.inflate(R.layout.fragment_sign_up, container, false)
        val edtTxtSignUpEmail = view.findViewById<EditText>(R.id.edtEmail)
        val edtTxtSignUpPassword = view.findViewById<EditText>(R.id.edtSenha)
        val edtTxtSignUpRePassword = view.findViewById<EditText>(R.id.edtConfirmSenha)
        val btnCadastrar = view.findViewById<Button>(R.id.btnCadastrar)
        val viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)

        btnCadastrar.setOnClickListener {
            try {
                viewModel.createUser(
                    edtTxtSignUpEmail,
                    edtTxtSignUpPassword,
                    edtTxtSignUpRePassword
                )
                    .addOnSuccessListener {
                        viewModel.firebaseUser = it.user
                        Toast.makeText(
                            this.context,
                            "Cadastro realizado com sucesso.",
                            Toast.LENGTH_LONG).show()
                        parentFragmentManager.beginTransaction()
                            .replace(R.id.fragmentContainerView, SignInFragment()).commit()
                    }.addOnFailureListener {
                        Toast.makeText(
                            this.context,
                            it.message,
                            Toast.LENGTH_LONG).show()
                    }
                Toast.makeText(
                    this.context,
                    "Efetuando Cadastro.",
                    Toast.LENGTH_LONG).show()
            } catch (e: Throwable) {
                Toast.makeText(
                    this.context,
                    e.message,
                    Toast.LENGTH_LONG).show()
            }
        }

        return view

    }

}