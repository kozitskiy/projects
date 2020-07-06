package com.example.film_search.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.children
import androidx.fragment.app.Fragment
import com.example.film_search.R
import com.example.film_search.db.Cache
import com.example.film_search.network.manageRequests.ApiRequestMovieSingle
import com.example.film_search.utils.HelpUtils
import io.reactivex.disposables.Disposable
import kotlinx.android.synthetic.main.fragment_info.*


private const val ID_PARAM = "ID"

class InfoFragment : Fragment() {
    private var movieId: String = "0"
    private var disposableApi: Disposable? = null

    companion object {
        const val TAG = "InfoFragment"
        var viewGroupParent: ViewGroup? = null
        fun newInstance(idFilm: String) =
            InfoFragment().apply {
                arguments = Bundle().apply {
                    putString(ID_PARAM, idFilm)
                }
            }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            movieId = it.getString(ID_PARAM).toString()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewGroupParent = container
        return inflater.inflate(R.layout.fragment_info, container, false)
    }

    @SuppressLint("InflateParams")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val viewConnection: View =
            layoutInflater.inflate(R.layout.item_connection, viewGroupParent, false)

        val viewProgress: View =
            layoutInflater.inflate(R.layout.item_progress, viewGroupParent, false)

        Cache(requireContext().applicationContext).getMoviesDetails(movieId)?.let {
            HelpUtils.setMovieDetails(this, it)
        } ?: run {
            movie_element.children.forEach { it.visibility = View.GONE }
            movie_element.addView(viewProgress)
            val apiRequestCocktailFull =
                ApiRequestMovieSingle(this, viewConnection, viewProgress, movieId)
            disposableApi = apiRequestCocktailFull.doRequest()
        }
    }

    override fun onStop() {
        super.onStop()
        disposableApi?.dispose()
    }

}