package com.foliveira.pacientes.meusfilmes.model.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface MovieDAO {

    @Query("SELECT * FROM MovieDB")
    fun getFavoriteMovies() : List<MovieDB>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addMovie(movie: MovieDB)

    @Delete
    fun deleteMovie(movie: MovieDB)

}