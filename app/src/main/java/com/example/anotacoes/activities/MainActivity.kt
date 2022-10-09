package com.example.anotacoes.activities

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.anotacoes.*
import com.example.anotacoes.database.Anotacoes
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.firebase.auth.FirebaseAuth
import org.w3c.dom.Text
import kotlin.random.Random

class MainActivity : AppCompatActivity(),
    ClickInterface,
    ClickDeleteInterface {
    lateinit var viewModel: AnotacoesViewModel
    lateinit var recyclerView: RecyclerView
    lateinit var mAdView: AdView
    lateinit var mAuth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


      mAdView = findViewById(R.id.adView)
        val adRequest = AdRequest.Builder().build()
        mAdView.loadAd(adRequest)

        val tvEmail = findViewById<TextView>(R.id.tvEmail)

        val email = intent.getStringExtra("email")
        tvEmail.text = email.toString()


        val tvSair = findViewById<TextView>(R.id.tvSair)
        tvSair.setOnClickListener {
            mAuth.signOut()
            startActivity(Intent(this, LoginActivity::class.java))
            this.finish()
        }

        viewModel = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        ).get(AnotacoesViewModel::class.java)


        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        val foodAdapter = AnotacoesAdapter(this, this,this)
        recyclerView.adapter = foodAdapter

        viewModel.listaAnotacoes.observe(this, Observer { list -> list?.let{
            foodAdapter.updateList(it)
        }
        })



        val bNovaAnotacao = findViewById<Button>(R.id.bNovaAnotação)
        bNovaAnotacao.setOnClickListener{
            val intent = Intent(this, NovaAnotacaoActivity::class.java)
            startActivity(intent)
        }

    }

    override fun onClickInterface(anotacao: Int){
        val intent = Intent(this, AnotacaoActivity::class.java)
        intent.putExtra("position", anotacao)

        startActivity(intent)
    }

    override fun onDeleteInterface(anotacao: Anotacoes) {
        // in on note click method we are calling delete
        // method from our view modal to delete our not.
        viewModel.deleteAnotacao(anotacao)
        // displaying a toast message
        Toast.makeText(this, "Anotação ${anotacao.titulo} deletada", Toast.LENGTH_LONG).show()
    }

}