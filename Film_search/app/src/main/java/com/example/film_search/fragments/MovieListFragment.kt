package com.example.film_search.fragments

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.film_search.db.navigation.LazyLoadMovies
import com.example.film_search.R
import com.example.film_search.SPAN_COUNT_MOVIES_IN_GRID
import com.example.film_search.adapters.MoviesAdapter
import com.example.film_search.db.Cache
import com.example.film_search.db.navigation.SearchFilterMovies
import com.example.film_search.listeners.interfaces.ActivityNavigationListeners
import com.example.film_search.listeners.interfaces.OnMovieClickListener
import com.example.film_search.network.manageRequests.ApiRequestMoviePreviews
import io.reactivex.disposables.Disposable
import kotlinx.android.synthetic.main.fragment_movie_list.*

class MovieListFragment : Fragment(),
    OnMovieClickListener {

    private lateinit var moviesAdapter: MoviesAdapter
    private var disposableApi: Disposable? = null

    companion object {
        const val TAG = "MovieListFragment"
        private var navigation: ActivityNavigationListeners? = null
        fun newInstance() = MovieListFragment()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        when (context) {
            is ActivityNavigationListeners -> navigation = context
            else -> throw IllegalArgumentException(context.javaClass.name + " not implemented ActivityNavigationListeners")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_movie_list, container, false)


    @SuppressLint("ShowToast")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        moviesAdapter = MoviesAdapter(this)

        recycler_cocktail_list.apply {
            layoutManager = GridLayoutManager(requireContext(), SPAN_COUNT_MOVIES_IN_GRID)
            adapter = moviesAdapter
        }

        val cache = Cache(requireContext().applicationContext)

        cache.getMoviePreviewsByFixedLimit()?.let {
            moviesAdapter.update(it)
        } ?: run {
            val apiRequestMoviePreviews = ApiRequestMoviePreviews(this, null, moviesAdapter)
            disposableApi = apiRequestMoviePreviews.doRequest()
        }

        LazyLoadMovies(recycler_cocktail_list, cache, moviesAdapter, search).activate()

        SearchFilterMovies(search, moviesAdapter).activate()
    }

    override fun onMovieClickListener(movieId: String) {
        navigation?.showSingleMoviePage(movieId)
    }

    override fun onStop() {
        super.onStop()
        disposableApi?.dispose()
    }
}