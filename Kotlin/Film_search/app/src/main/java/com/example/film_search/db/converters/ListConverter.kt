package com.example.film_search.db.converters

import androidx.room.TypeConverter
import com.example.film_search.db.model.ProductionCountry
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class ListConverter {

    private val gson = Gson()
    private val type = object : TypeToken<List<ProductionCountry>>() {}.type

    @TypeConverter
    fun toProductionCountry(countries: List<ProductionCountry>?): String? =
        gson.toJson(countries)

    @TypeConverter
    fun fromProductionCountry(data: String): List<ProductionCountry>? =
        gson.fromJson(data, type)
}