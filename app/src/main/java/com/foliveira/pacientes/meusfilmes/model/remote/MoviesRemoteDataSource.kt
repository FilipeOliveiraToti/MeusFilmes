package com.foliveira.pacientes.meusfilmes.model.remote

import com.foliveira.pacientes.meusfilmes.model.modelos.Movie
import com.foliveira.pacientes.meusfilmes.model.remote.modelos.MovieRemote
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class MoviesRemoteDataSource(
    private val moviesApi: MoviesApi
) : IMoviesRemoteDataSource {
    override suspend fun getMovies(): Flow<List<Movie>> {
        val movies = moviesApi.getPopularMovies(page = 1)
        return flowOf(movies.results.map { movieRemoteToMovie(it) })
    }

    private fun movieRemoteToMovie(movieRemote: MovieRemote): Movie {
        return Movie(
            id = movieRemote.id,
            title = movieRemote.title,
            posterUrl = movieRemote.getPosterURL(),
            isFavorite = false
        )
    }
}
