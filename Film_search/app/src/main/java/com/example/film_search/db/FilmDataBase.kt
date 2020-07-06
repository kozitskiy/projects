package com.example.film_search.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.film_search.db.converters.ListConverter
import com.example.film_search.db.model.Film
import com.example.film_search.db.model.FilmSingle

@Database(entities = [Film::class, FilmSingle::class], version = 2, exportSchema = false)
@TypeConverters(ListConverter::class)
abstract class FilmDataBase : RoomDatabase() {

    abstract fun getFilmDao(): FilmDao

    companion object {
        fun getInstance(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            FilmDataBase::class.java,
            "films"
        ).fallbackToDestructiveMigration()
            .allowMainThreadQueries()
            .build()
    }

}