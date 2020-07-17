package com.example.film_search.db

import android.content.Context
import com.example.film_search.App
import com.example.film_search.db.model.Film
import com.example.film_search.db.model.FilmSingle

class Cache(
    context: Context
) {
    private val dao = (context.applicationContext as App).db.getFilmDao()

    fun addMoviePreviews(films: List<Film>) {
        dao.insertFilms(films)
    }

    fun addCocktailsDetails(films: FilmSingle) {
        dao.insertSingleFilm(films)
    }

    fun getMoviePreviewsByFixedLimit() = dao.selectFilms().let {
        if (it.isNotEmpty()) it
        else null
    }

    fun getMoviePreviewsByWithinLimit(from: Int) = dao.selectFilmsFromPos(from).let {
        if (it.isNotEmpty()) it
        else null
    }

    fun getMoviesDetails(filmId: String): FilmSingle? {
        return dao.selectSingleFilmByID(filmId.toInt())
    }

}