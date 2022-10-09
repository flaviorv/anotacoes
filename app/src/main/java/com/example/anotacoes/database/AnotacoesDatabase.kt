package com.example.anotacoes.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [Anotacoes::class], version = 1)
@TypeConverters(Converters::class)
abstract class AnotacoesDatabase: RoomDatabase() {
    abstract fun obterAnotacoesDao(): AnotacoesDao

    companion object {

        @Volatile
        private var INSTANCE: AnotacoesDatabase? = null

        fun getDatabase(context: Context): AnotacoesDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AnotacoesDatabase::class.java,
                    "anotacoes_database"
                ).build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }
}