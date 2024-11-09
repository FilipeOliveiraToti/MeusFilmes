package com.foliveira.pacientes.meusfilmes.model.remote.modelos

data class MovieRemote(
    val id: Int,
    val poster_path: String,
    val title: String,
) {

    fun getPosterURL() = "https://image.tmdb.org/t/p/w500${poster_path}"
}