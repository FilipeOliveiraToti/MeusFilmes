package com.foliveira.pacientes.meusfilmes.model.remote.modelos

data class MovieRemoteResponse(
    val page: Int,
    val results: List<MovieRemote>,
    val total_pages: Int,
    val total_results: Int
)