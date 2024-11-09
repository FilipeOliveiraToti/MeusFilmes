package com.foliveira.pacientes.meusfilmes.model.remote

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitConfig  {

    var service: MoviesApi = getMovieService()

    private fun getRetrofitBuild() : Retrofit {

        return Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(getOkHttpClient())
            .build()
    }

    private fun getOkHttpClient(): OkHttpClient {
        //TODO: Trocar a API Key por uma que seja válida
        return OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .addInterceptor(ApiKeyInterceptor("INSIRA A SUA API KEY AQUI"))
            .build()
    }

    private fun getMovieService(): MoviesApi {
        return getRetrofitBuild().create(MoviesApi::class.java)
    }
}

class ApiKeyInterceptor(private val apiKey: String) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val urlWithApiKey = originalRequest.url().newBuilder()
            .addQueryParameter("api_key", apiKey)
            .addQueryParameter("language", "pt-BR")
            .build()

        val newRequest = originalRequest.newBuilder()
            .url(urlWithApiKey)
            .build()

        return chain.proceed(newRequest)
    }
}