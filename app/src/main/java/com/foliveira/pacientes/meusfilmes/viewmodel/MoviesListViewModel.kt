package com.foliveira.pacientes.meusfilmes.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.foliveira.pacientes.meusfilmes.model.MoviesRepository
import com.foliveira.pacientes.meusfilmes.model.modelos.Movie
import com.foliveira.pacientes.meusfilmes.ui.modelos.MovieUI
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MoviesListViewModel(
    private val moviesRepository: MoviesRepository
) : ViewModel() {

    private val _movies = MutableLiveData<List<MovieUI>>()
    val movies: LiveData<List<MovieUI>>
        get() = _movies

    fun loadMovies() {
        viewModelScope.launch(Dispatchers.IO) {
            moviesRepository.getMovies().collect { movies ->
                // Converter a lista de filmes para a classe MovieUI
                val filmesConvertidos = movies.map { movie ->
                    MovieUI(
                        id = movie.id,
                        title = movie.title,
                        posterUrl = movie.posterUrl,
                        isFavorite = movie.isFavorite
                    )

                }
                _movies.postValue(filmesConvertidos)
            }
        }
    }

    fun changeFavoriteStatus(movie: MovieUI) {
        val movieList = _movies.value?.toMutableList() ?: mutableListOf()
        val movieIndex = movieList.indexOfFirst { it.id == movie.id }

        if (movie.isFavorite) {
            deleteFavoriteMovie(movie)
            val movieUpdated = movie.copy(isFavorite = false)
            movieList[movieIndex] = movieUpdated
        } else {
            saveFavoriteMovie(movie)
            val movieUpdated = movie.copy(isFavorite = true)
            movieList[movieIndex] = movieUpdated
        }

        _movies.value = movieList
    }

    private fun saveFavoriteMovie(movie: MovieUI) {
        viewModelScope.launch(Dispatchers.IO) {
            val filmeConvertido = Movie(
                id = movie.id,
                title = movie.title,
                posterUrl = movie.posterUrl,
                isFavorite = movie.isFavorite
            )
            moviesRepository.saveFavoriteMovie(filmeConvertido)
        }
    }

    private fun deleteFavoriteMovie(movie: MovieUI) {
        viewModelScope.launch(Dispatchers.IO) {
            val filmeConvertido = Movie(
                id = movie.id,
                title = movie.title,
                posterUrl = movie.posterUrl,
                isFavorite = movie.isFavorite
            )
            moviesRepository.deleteFavoriteMovie(filmeConvertido)
        }
    }
}
