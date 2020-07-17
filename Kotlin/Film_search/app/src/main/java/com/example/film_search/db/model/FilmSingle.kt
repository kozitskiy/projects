package com.example.film_search.db.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.film_search.db.converters.ListConverter
import com.google.gson.annotations.SerializedName

@Entity
data class FilmSingle(
    @PrimaryKey(autoGenerate = true)
    val idDb: Long,
    @SerializedName("id")
    val movieId: Int,
    val title: String,
    @SerializedName("poster_path")
    val posterPath: String,
    val overview: String,
    @SerializedName("release_date")
    val releaseDate: String? = null,
    @SerializedName("original_language")
    val originalLanguage: String? = null,
    val runtime: Int,
    @TypeConverters(ListConverter::class)
    @SerializedName("production_countries")
    val productionCountries: List<ProductionCountry>? = null
)

data class ProductionCountry(
    val name: String
)