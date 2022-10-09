package com.example.anotacoes

import android.app.Application
import android.graphics.Bitmap
import android.net.Uri
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.anotacoes.database.Anotacoes
import com.example.anotacoes.database.AnotacoesDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AnotacoesViewModel(application: Application): AndroidViewModel(application) {
    val listaAnotacoes: LiveData<List<Anotacoes>>
    val repository: Repository
    val imagem_camera = MutableLiveData<Bitmap>()





    init{
        val dao = AnotacoesDatabase.getDatabase(application).obterAnotacoesDao()
        repository = Repository(dao)
        listaAnotacoes = repository.listaAnotacoes

    }

    fun deleteAnotacao(anotacao: Anotacoes) =viewModelScope.launch(Dispatchers.IO){
        repository.deleteAnotacao(anotacao)
    }

    fun updateAnotacao(anotacao: Anotacoes) =viewModelScope.launch(Dispatchers.IO) {
        repository.updateAnotacao(anotacao)
    }

    fun insertAnotacao(anotacao: Anotacoes) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(anotacao)
    }

}
