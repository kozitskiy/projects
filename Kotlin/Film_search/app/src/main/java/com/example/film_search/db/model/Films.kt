package com.example.film_search.db.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

data class Films(
    val results: List<Film>
)

@Entity
data class Film(
    @PrimaryKey(autoGenerate = true)
    val idDb: Long,
    @SerializedName("id")
    val movieId: Int,
    val title: String,
    @SerializedName("poster_path")
    val posterPath: String,
    @SerializedName("vote_average")
    val voteAverage: Double,
    val overview: String,
    @SerializedName("release_date")
    val releaseDate: String
)