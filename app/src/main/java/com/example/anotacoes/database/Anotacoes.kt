package com.example.anotacoes.database

import android.graphics.Bitmap
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class Anotacoes( val titulo: String,
               val anotacao: String,
                 val foto: Bitmap,
                 @PrimaryKey(autoGenerate = true) var id: Int = 0
){

}