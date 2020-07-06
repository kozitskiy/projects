package com.example.film_search.db.navigation

import android.widget.SearchView
import androidx.recyclerview.widget.RecyclerView
import com.example.film_search.COUNT_MOVIES_ON_LOAD
import com.example.film_search.adapters.MoviesAdapter
import com.example.film_search.db.Cache

class LazyLoadMovies(
    private val recyclerView: RecyclerView,
    private val cache: Cache,
    private val moviesAdapter: MoviesAdapter,
    private val search: SearchView
) {
    private var loadFrom = COUNT_MOVIES_ON_LOAD

    fun activate() {
        recyclerView.viewTreeObserver
            .addOnScrollChangedListener {
                if (isLastElement()) {
                    cache.getMoviePreviewsByWithinLimit(loadFrom)?.let {
                        moviesAdapter.update(it)
                        moviesAdapter.filter(search.query.toString())
                    }
                    loadFrom *= 2
                }
            }
    }

    private fun isLastElement() = if (recyclerView.childCount != 0) {
        recyclerView.getChildAt(recyclerView.childCount - 1).bottom - (recyclerView.height + recyclerView.scrollY) <= 0
    } else false

}