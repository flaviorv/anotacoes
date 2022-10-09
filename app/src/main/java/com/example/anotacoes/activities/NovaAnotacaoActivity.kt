package com.example.anotacoes.activities

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.provider.MediaStore.Images.Media.getBitmap
import android.widget.*
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.anotacoes.AnotacoesViewModel
import com.example.anotacoes.R
import com.example.anotacoes.database.Anotacoes

class NovaAnotacaoActivity : AppCompatActivity() {

    private lateinit var resultLauncher: ActivityResultLauncher<Intent>

    private var imagemBitmap: Bitmap? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nova_anotacao)

        val viewModel = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        ).get(AnotacoesViewModel::class.java)


        val etTitulo = findViewById<EditText>(R.id.etTitulo)
        val etData = findViewById<EditText>(R.id.etData)
        val etAnotacao = findViewById<EditText>(R.id.etAnotacao)
        val salvarButton = findViewById<Button>(R.id.bSalvarAnotacao)
        val ivFoto = findViewById<ImageView>(R.id.ivFoto)

        fun handleCameraImage(intent: Intent?) {
            val bitmap = intent?.extras?.get("data") as Bitmap
            viewModel.imagem_camera.value = bitmap
            ivFoto.setImageBitmap(viewModel.imagem_camera.value)
            imagemBitmap = bitmap
        }

        resultLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                if (result.resultCode == Activity.RESULT_OK) {
                    handleCameraImage(result.data)
                }
            }



        ivFoto.setOnClickListener{

        val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            resultLauncher.launch(cameraIntent)

        }

        viewModel.imagem_camera.observe(this, Observer {
                carregamento -> ivFoto.setImageBitmap(carregamento)
        })




        salvarButton.setOnClickListener {
            val titulo = "${etTitulo.text}(${etData.text})"
            val anotacao = etAnotacao.text.toString()

            if (titulo.isNotEmpty() && anotacao.isNotEmpty() && imagemBitmap != null) {
                viewModel.insertAnotacao(Anotacoes(
                    titulo = titulo, anotacao = anotacao, foto = imagemBitmap!!))
                Toast.makeText(this, "Anotação Salvo", Toast.LENGTH_SHORT).show()

                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                this.finish()

            }else {
                Toast.makeText(this, "Dados incompletos", Toast.LENGTH_SHORT).show()
            }

        }

    }


}

