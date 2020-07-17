package com.example.film_search

import android.app.Application
import com.example.film_search.db.FilmDataBase

class App: Application() {
    lateinit var db: FilmDataBase

    override fun onCreate() {
        super.onCreate()
        db = FilmDataBase.getInstance(this)
    }
}
