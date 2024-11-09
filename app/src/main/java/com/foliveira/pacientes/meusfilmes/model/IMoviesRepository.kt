package com.foliveira.pacientes.meusfilmes.model

import com.foliveira.pacientes.meusfilmes.model.modelos.Movie
import kotlinx.coroutines.flow.Flow

interface IMoviesRepository {
    suspend fun getMovies(): Flow<List<Movie>>

    suspend fun saveFavoriteMovie(movie: Movie)

    suspend fun deleteFavoriteMovie(movie: Movie)

}