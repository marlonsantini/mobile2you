package br.marlon.mobile2you.network.service

import br.marlon.mobile2you.feature.moviedetail.data.api.MovieClient
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInitializer {

    private const val BASE_URL = "https://api.themoviedb.org/3/movie/"

    private val okHttpClient = OkHttpClient.Builder()
        .build()

    val instance: MovieClient by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()

        retrofit.create(MovieClient::class.java)
    }
}