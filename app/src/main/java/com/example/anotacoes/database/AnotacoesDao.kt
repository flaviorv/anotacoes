package com.example.anotacoes.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface AnotacoesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert( anotacao: Anotacoes)

    @Delete
    suspend fun delete(anotacao: Anotacoes)

    @Query("Select * From Anotacoes order by id ASC")
    fun obterAnotacoes(): LiveData<List<Anotacoes>>


    @Update
    suspend fun update(anotacao: Anotacoes)
}