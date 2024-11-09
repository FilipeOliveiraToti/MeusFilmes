package com.foliveira.pacientes.meusfilmes.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.foliveira.pacientes.meusfilmes.R
import com.foliveira.pacientes.meusfilmes.ui.decoration.GridItemDecoration
import com.foliveira.pacientes.meusfilmes.viewmodel.MoviesListViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MoviesListFragment : Fragment() {

    private lateinit var moviesRecyclerView: RecyclerView
    private val moviesListViewModel: MoviesListViewModel by viewModel()
    private lateinit var moviesAdapter: MoviesAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_movies_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        moviesRecyclerView = view.findViewById(R.id.rv_movie_list)

        setupRecyclerView()
        setupObservers()

        moviesListViewModel.loadMovies()
    }

    private fun setupRecyclerView() {
        moviesAdapter = MoviesAdapter {
            moviesListViewModel.changeFavoriteStatus(it)
        }

        val layoutManager = GridLayoutManager(requireContext(), 2)

        //Responsável por deixar o espaçamento entre os itens da recyclerView
        val itemDecoration = GridItemDecoration(2, R.dimen.dimen_12dp)

        moviesRecyclerView.layoutManager = layoutManager
        moviesRecyclerView.addItemDecoration(itemDecoration)
        moviesRecyclerView.adapter = moviesAdapter
    }

    private fun setupObservers() {
        moviesListViewModel.movies.observe(viewLifecycleOwner) { movies ->
            moviesAdapter.updateMovies(movies)
        }
    }
}
