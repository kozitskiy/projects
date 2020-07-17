package com.example.kozitskiy_com.network

import com.example.kozitskiy_com.network.models.DataApiObjects
import com.example.kozitskiy_com.network.models.DataEmailRequest
import com.example.kozitskiy_com.network.models.DataEmailResponse
import io.reactivex.Observable
import io.reactivex.Single
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

object ApiService {
    private const val END_POINT: String = "https://kozitskiy.com/"
    private var kozitskiyApi: KozitskiyApi

    fun getResponseData() = kozitskiyApi.responseData()
    fun getResponseEmail(body: DataEmailRequest) =
        kozitskiyApi.responseEmail(body)

    interface KozitskiyApi {
        @GET("api/get_json")
        fun responseData(): Single<DataApiObjects>

        @POST("php/process.php")
        fun responseEmail(@Body body: DataEmailRequest): Observable<DataEmailResponse>
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
        kozitskiyApi = retrofit.create(
            KozitskiyApi::class.java
        )
    }
}