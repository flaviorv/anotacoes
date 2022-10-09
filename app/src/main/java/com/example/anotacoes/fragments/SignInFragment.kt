package com.example.anotacoes.fragments

import android.content.Intent
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
import com.example.anotacoes.activities.MainActivity

class SignInFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_sign_in, container, false)
        val btnTelaCadastro = view.findViewById<Button>(R.id.btnTelaCadastro)
        val btnEntrar = view.findViewById<Button>(R.id.btnEntrar)
        val edtEmail = view.findViewById<EditText>(R.id.edtEmailAcesso)
        val edtSenha = view.findViewById<EditText>(R.id.edtSenhaAcesso)
        val viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)



        btnEntrar.setOnClickListener {
            try {
                viewModel.signInUser(
                    edtEmail, edtSenha
                ).addOnSuccessListener {
                    if (it != null){
                        Toast.makeText(
                            this.context,
                            "Bem vindo ${it.user?.email}!",
                            Toast.LENGTH_LONG
                        ).show()
                        val intent = Intent(activity, MainActivity::class.java)
                        intent.putExtra("email", "${it.user?.email}")
                        startActivity(intent)
                    }
                }.addOnFailureListener {
                    Toast.makeText(
                        this.context,
                        it.message,
                        Toast.LENGTH_LONG
                    ).show()
                }
            } catch (e: Throwable){
                Toast.makeText(
                    this.context,
                    e.message,
                    Toast.LENGTH_LONG
                ).show()
            }
        }

        btnTelaCadastro.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainerView, SignUpFragment()).commit()
        }

        return view
    }

}