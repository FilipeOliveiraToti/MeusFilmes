package com.foliveira.pacientes.meusfilmes.model

import com.foliveira.pacientes.meusfilmes.model.local.MoviesLocalDataSource
import com.foliveira.pacientes.meusfilmes.model.modelos.Movie
import com.foliveira.pacientes.meusfilmes.model.remote.MoviesRemoteDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class MoviesRepository(
    private val moviesRemoteDataSource: MoviesRemoteDataSource,
    private val moviesLocalDataSource: MoviesLocalDataSource
) : IMoviesRepository {

    override suspend fun getMovies(): Flow<List<Movie>> {
        return moviesRemoteDataSource.getMovies().map { list ->
            list.map { movie ->

                val isFavorite = getFavoriteMovies().any {
                    it.id == movie.id
                }

                Movie(
                    id = movie.id,
                    title = movie.title,
                    posterUrl = movie.posterUrl,
                    isFavorite = isFavorite
                )
            }
        }
    }

    private suspend fun getFavoriteMovies(): List<Movie> {
        return moviesLocalDataSource.getFavoriteMovies()
    }

    override suspend fun saveFavoriteMovie(movie: Movie) {
        moviesLocalDataSource.saveFavoriteMovie(movie)
    }

    override suspend fun deleteFavoriteMovie(movie: Movie) {
        moviesLocalDataSource.deleteFavoriteMovie(movie)
    }

}