package com.example.anotacoes

import androidx.lifecycle.LiveData
import com.example.anotacoes.database.Anotacoes
import com.example.anotacoes.database.AnotacoesDao

class Repository(private val anotacoesDao: AnotacoesDao) {

    val listaAnotacoes: LiveData<List<Anotacoes>> = anotacoesDao.obterAnotacoes()

    suspend fun insert(anotacao: Anotacoes) { anotacoesDao.insert(anotacao)}

    suspend fun deleteAnotacao(anotacao: Anotacoes) { anotacoesDao.delete(anotacao)}

    suspend fun updateAnotacao (anotacao: Anotacoes) { anotacoesDao.update(anotacao)}
}