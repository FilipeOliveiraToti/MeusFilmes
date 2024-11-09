package com.foliveira.pacientes.meusfilmes.model.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [MovieDB::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getMoviesDAO() : MovieDAO

    companion object {
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "app_database"
                )
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}