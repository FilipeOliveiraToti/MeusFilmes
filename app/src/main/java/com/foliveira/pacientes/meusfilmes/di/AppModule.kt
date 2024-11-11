package com.foliveira.pacientes.meusfilmes.di

import com.foliveira.pacientes.meusfilmes.model.IMoviesRepository
import com.foliveira.pacientes.meusfilmes.model.local.MoviesLocalDataSource
import com.foliveira.pacientes.meusfilmes.model.remote.MoviesRemoteDataSource
import com.foliveira.pacientes.meusfilmes.model.MoviesRepository
import com.foliveira.pacientes.meusfilmes.model.local.AppDatabase
import com.foliveira.pacientes.meusfilmes.model.local.IMoviesLocalDataSource
import com.foliveira.pacientes.meusfilmes.model.remote.IMoviesRemoteDataSource
import com.foliveira.pacientes.meusfilmes.model.remote.MoviesApi
import com.foliveira.pacientes.meusfilmes.model.remote.RetrofitConfig
import com.foliveira.pacientes.meusfilmes.viewmodel.MoviesListViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel {
        MoviesListViewModel(get())
    }
    single {
        AppDatabase.getDatabase(androidContext())
    }
    factory<MoviesApi> {
        RetrofitConfig.service
    }
    factory<MoviesLocalDataSource> {
        MoviesLocalDataSource(get())
    }
    singleOf(::MoviesRemoteDataSource) { bind<IMoviesRemoteDataSource>() }
    singleOf(::MoviesLocalDataSource) { bind<IMoviesLocalDataSource>() }
    singleOf(::MoviesRepository) { bind<IMoviesRepository>() }
}