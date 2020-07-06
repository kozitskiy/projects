package com.example.film_search.network

import com.example.film_search.db.model.FilmSingle
import com.example.film_search.db.model.Films
import io.reactivex.Single
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

object ApiService {
    private const val END_POINT: String = "https://api.themoviedb.org/3/movie/"
    private const val API_KEY = "0b6b796d31e116b2fe20ae1c02d9dc83"
    private var filmsApi: FilmsApi

    fun getPopular() = filmsApi.allPopularFilms(API_KEY)
    fun getSingle(movieId: String) = filmsApi.singleFilmById(movieId, API_KEY)

    interface FilmsApi {
        @GET("popular")
        fun allPopularFilms(@Query("api_key") apiKey: String): Single<Films>

        @GET("{movie_id}")
        fun singleFilmById(
            @Path("movie_id") movieId: String,
            @Query("api_key") apiKey: String
        ): Single<FilmSingle>
    }

    init {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        val client = OkHttpClient.Builder().addInterceptor(interceptor).build()
        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .baseUrl(END_POINT)
            .client(client)
            .build()
        filmsApi = retrofit.create(FilmsApi::class.java)
    }
}