package com.example.film_search.network.manageRequests

import android.view.View
import androidx.fragment.app.Fragment
import com.example.film_search.adapters.MoviesAdapter
import com.example.film_search.db.Cache
import com.example.film_search.network.ApiService
import com.example.film_search.db.model.Films
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class ApiRequestMoviePreviews(
    fragment: Fragment,
    viewConnection: View? = null,
    private val cocktailsAdapter: MoviesAdapter
) : ApiRequest(fragment, viewConnection) {

    override fun doRequest(): Disposable =
        ApiService.getPopular()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(this::success, this::error)

    override fun <T> success(moviesData: T) {
        val movies = (moviesData as Films).results
        Cache(fragment.requireContext()).addMoviePreviews(movies)
        cocktailsAdapter.update(movies)
    }
}
