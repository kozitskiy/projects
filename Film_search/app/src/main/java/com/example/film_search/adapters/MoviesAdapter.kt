package com.example.film_search.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.film_search.R
import com.example.film_search.extenations.containsLowCase
import com.example.film_search.listeners.interfaces.OnMovieClickListener
import com.example.film_search.db.model.Film
import com.example.film_search.utils.HelpUtils
import kotlinx.android.synthetic.main.item_movie.view.*

class MoviesAdapter(private val movieClickListener: OnMovieClickListener) :
    RecyclerView.Adapter<MoviesAdapter.DataAnimalFactHolder>() {
    private val movieList = ArrayList<Film>()
    private var filteredMovieList = ArrayList<Film>()

    fun update(movies: List<Film>) {
        movieList.apply {
            addAll(movies)
        }
        filteredMovieList.apply {
            clear()
            addAll(movieList)
        }
        notifyDataSetChanged()
    }

    fun filter(text: String) {
        filteredMovieList.apply {
            clear()
            addAll(
                movieList.filter {
                    it.title.containsLowCase(text) ||
                            it.overview.containsLowCase(text) ||
                            it.releaseDate.containsLowCase(text)
                }
            )
        }
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataAnimalFactHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)
        return DataAnimalFactHolder(itemView, movieClickListener)
    }

    override fun getItemCount() = filteredMovieList.size

    override fun onBindViewHolder(holder: DataAnimalFactHolder, position: Int) {
        holder.bind(filteredMovieList[position])
    }

    class DataAnimalFactHolder(
        itemView: View,
        private val movieClickListener: OnMovieClickListener
    ) :
        RecyclerView.ViewHolder(itemView) {

        fun bind(movie: Film) {
            HelpUtils.setMoviePreview(itemView, movie)
            itemView.movie_element.setOnClickListener {
                movieClickListener.onMovieClickListener(
                    movie.movieId.toString()
                )
            }
        }
    }
}