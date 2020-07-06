package com.example.film_search.db.navigation

import android.widget.SearchView
import com.example.film_search.adapters.MoviesAdapter

class SearchFilterMovies(
    private val search: SearchView,
    private val moviesAdapter: MoviesAdapter
) {

    fun activate() {
        search.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                moviesAdapter.filter(search.query.toString())
                return false
            }
        })
    }
}