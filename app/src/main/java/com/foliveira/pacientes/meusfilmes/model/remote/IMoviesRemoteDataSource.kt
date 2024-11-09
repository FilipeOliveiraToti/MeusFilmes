package com.foliveira.pacientes.meusfilmes.model.remote

import com.foliveira.pacientes.meusfilmes.model.modelos.Movie
import kotlinx.coroutines.flow.Flow

interface IMoviesRemoteDataSource {
    suspend fun getMovies(): Flow<List<Movie>>
}