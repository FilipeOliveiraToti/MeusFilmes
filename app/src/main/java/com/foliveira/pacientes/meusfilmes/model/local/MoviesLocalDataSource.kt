package com.foliveira.pacientes.meusfilmes.model.local

import com.foliveira.pacientes.meusfilmes.model.modelos.Movie

class MoviesLocalDataSource(
    private val appDatabase: AppDatabase
) : IMoviesLocalDataSource {

    override suspend fun getFavoriteMovies(): List<Movie> {
        val favoriteMovies = appDatabase.getMoviesDAO().getFavoriteMovies()
        val filmesConvertidos =
            favoriteMovies.map { filme -> movieDBToMovie(filme) }
        return filmesConvertidos
    }

    override suspend fun saveFavoriteMovie(movie: Movie) {
        appDatabase.getMoviesDAO().addMovie(movieToMovieDB(movie))
    }

    override suspend fun deleteFavoriteMovie(movie: Movie) {
        appDatabase.getMoviesDAO().deleteMovie(movieToMovieDB(movie))
    }


    private fun movieDBToMovie(it: MovieDB): Movie {
        return Movie(
            id = it.id,
            title = it.title,
            posterUrl = it.poster_path,
            isFavorite = true
        )
    }

    private fun movieToMovieDB(it: Movie): MovieDB {
        return MovieDB(
            id = it.id,
            title = it.title,
            poster_path = it.posterUrl
        )
    }
}
