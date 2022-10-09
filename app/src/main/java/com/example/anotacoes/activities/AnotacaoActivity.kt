package com.example.anotacoes.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.anotacoes.AnotacoesViewModel
import com.example.anotacoes.R
import com.example.anotacoes.database.Anotacoes

class AnotacaoActivity : AppCompatActivity() {
    lateinit var viewModel: AnotacoesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_anotacao)



        val tvAnotacao = findViewById<TextView>(R.id.tvAnotacao)
        val tvTitulo2 = findViewById<TextView>(R.id.tvTitulo2)
        val ivImagem = findViewById<ImageView>(R.id.ivImagem)
        val position = intent.getIntExtra("position", 0)

        viewModel = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        ).get(AnotacoesViewModel::class.java)



        viewModel.listaAnotacoes.observe(this, Observer { list -> list?.let{
            tvTitulo2.text = it.get(position ).titulo
            tvAnotacao.text = it.get(position).anotacao
            ivImagem.setImageBitmap(it.get(position).foto)
        }
        })

    }
}