package com.example.film_search.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.film_search.COUNT_MOVIES_ON_LOAD
import com.example.film_search.db.model.Film
import com.example.film_search.db.model.FilmSingle

@Dao
interface FilmDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertFilms(filmsDB: List<Film>)

    @Query("SELECT * FROM Film LIMIT $COUNT_MOVIES_ON_LOAD")
    fun selectFilms(): List<Film>

    @Query("SELECT * FROM Film LIMIT :position, $COUNT_MOVIES_ON_LOAD")
    fun selectFilmsFromPos(position: Int): List<Film>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertSingleFilm(filmSingleDB: FilmSingle)

    @Query("SELECT * FROM FilmSingle WHERE movieId = :inputID")
    fun selectSingleFilmByID(inputID: Int): FilmSingle
}