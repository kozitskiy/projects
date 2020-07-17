package com.example.film_search.utils

import android.content.Context
import android.view.View
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.film_search.MainActivity
import com.example.film_search.R
import com.example.film_search.db.model.Film
import com.example.film_search.db.model.FilmSingle
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_info.*
import kotlinx.android.synthetic.main.item_movie.view.*

private const val IMAGE_ENDPOINT: String = "https://image.tmdb.org/t/p/w500/"

object HelpUtils {
    fun replaceFragment(
        context: MainActivity,
        fragment: Fragment,
        tag: String,
        addToBackStack: Boolean
    ) {
        val fragmentManager = context.supportFragmentManager
        if (fragmentManager.findFragmentByTag(tag) == null && addToBackStack) {
            fragmentManager.beginTransaction()
                .setCustomAnimations(R.anim.slide, R.anim.slide)
                .replace(context.fragment_container.id, fragment, tag)
                .addToBackStack(null)
                .commit()
        } else if (!addToBackStack) {
            fragmentManager.beginTransaction()
                .replace(context.fragment_container.id, fragment, tag)
                .commit()
        }
    }

    private fun setImageWithGlide(context: Context, imagePath: String, imageView: ImageView) {
        Glide
            .with(context)
            .load(IMAGE_ENDPOINT + imagePath)
            .into(imageView)
    }

    fun setMoviePreview(view: View, movie: Film) {
        setImageWithGlide(view.context, movie.posterPath, view.img_movie)
        view.txt_movie_name.text = movie.title
        view.txt_vote_average.text = movie.voteAverage.toString()
    }

    fun setMovieDetails(fragment: Fragment, movie: FilmSingle) {
        setImageWithGlide(fragment.requireContext(), movie.posterPath, fragment.poster)
        setImageWithGlide(fragment.requireContext(), movie.posterPath, fragment.background)
        fragment.title_info.text = movie.title
        fragment.overview_info.text = movie.overview
        fragment.runtime_info.text = movie.runtime.toString()
        fragment.original_language_info.text = movie.originalLanguage
        fragment.release_date_info.text = movie.releaseDate

        movie.productionCountries?.let { it ->
            fragment.production_countries_info.text = it.joinToString { it.name }
        } ?: run {
            fragment.production_countries.visibility = View.GONE
            fragment.production_countries_info.visibility = View.GONE
        }
    }
}