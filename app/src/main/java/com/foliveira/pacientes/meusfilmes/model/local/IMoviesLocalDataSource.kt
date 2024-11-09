package com.foliveira.pacientes.meusfilmes.model.local

import com.foliveira.pacientes.meusfilmes.model.modelos.Movie
import kotlinx.coroutines.flow.Flow

interface IMoviesLocalDataSource {
    suspend fun getFavoriteMovies(): List<Movie>

    suspend fun saveFavoriteMovie(movie: Movie)

    suspend fun deleteFavoriteMovie(movie: Movie)
}