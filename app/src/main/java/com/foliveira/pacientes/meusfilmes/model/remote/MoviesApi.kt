package com.foliveira.pacientes.meusfilmes.model.remote

import com.foliveira.pacientes.meusfilmes.model.remote.modelos.MovieRemoteResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface MoviesApi {

    @GET("movie/popular")
    suspend fun getPopularMovies(@Query("page") page: Int): MovieRemoteResponse
}
