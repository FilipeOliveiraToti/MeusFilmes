package com.foliveira.pacientes.meusfilmes.ui.fragments

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.foliveira.pacientes.meusfilmes.R
import com.foliveira.pacientes.meusfilmes.ui.modelos.MovieUI

class MoviesAdapter(
    val onItemClick: (MovieUI) -> Unit
) : RecyclerView.Adapter<MoviesAdapter.MoviesViewHolder>() {

    private val movieList = mutableListOf<MovieUI>()

    inner class MoviesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(movie: MovieUI) {
            val titleTextView = itemView.findViewById<TextView>(R.id.tv_movie_title)
            val posterImageView = itemView.findViewById<ImageView>(R.id.iv_movie_poster)

            // Carrega a imagem usando a biblioteca Glide
            Glide.with(itemView)
                .load(movie.posterUrl)
                .placeholder(R.drawable.ic_launcher_foreground)
                .into(posterImageView)

            titleTextView.text = movie.title

            itemView.setOnClickListener {
                onItemClick(movie)
            }

            if (movie.isFavorite) {
                val favoriteImageView = itemView.findViewById<ImageView>(R.id.iv_favorite)
                favoriteImageView.setImageResource(R.drawable.ic_heart_filled)
            } else {
                val favoriteImageView = itemView.findViewById<ImageView>(R.id.iv_favorite)
                favoriteImageView.setImageResource(R.drawable.ic_heart_outlined)
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)
        return MoviesViewHolder(view)
    }

    override fun getItemCount() = movieList.size

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        val movie = movieList[position]
        holder.bind(movie)
    }

    fun updateMovies(movies: List<MovieUI>) {
        movieList.clear()
        movieList.addAll(movies)
        notifyDataSetChanged()
    }
}