package com.example.film_search.network.manageRequests

import android.annotation.SuppressLint
import android.view.View
import androidx.core.view.children
import androidx.fragment.app.Fragment
import com.example.film_search.db.Cache
import com.example.film_search.network.ApiService
import com.example.film_search.db.model.FilmSingle
import com.example.film_search.utils.HelpUtils
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_info.*
import java.util.concurrent.TimeUnit

class ApiRequestMovieSingle(
    fragment: Fragment,
    viewConnection: View? = null,
    private val viewProgress: View? = null,
    private val movieId: String = "0"
) : ApiRequest(fragment, viewConnection) {

    override fun doRequest(): Disposable =
        ApiService.getSingle(movieId)
            .delay(1, TimeUnit.SECONDS)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(this::success, this::error)

    @SuppressLint("SetTextI18n")
    override fun <T> success(moviesData: T) {
        val movie = (moviesData as FilmSingle)
        Cache(fragment.requireContext()).addCocktailsDetails(movie)
        HelpUtils.setMovieDetails(fragment, movie)
        fragment.movie_element.children.forEach { it.visibility = View.VISIBLE }
        fragment.movie_element.removeView(viewProgress)
    }
}