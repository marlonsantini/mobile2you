package br.marlon.mobile2you.feature.moviedetail.data.api

import br.marlon.mobile2you.feature.moviedetail.data.model.Movie
import br.marlon.mobile2you.feature.moviedetail.data.model.MovieListSimilar
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

private const val APIKEY = "b62492831ba70a09a75a975f23e85216"

interface MovieClient {

    @GET("103")
    fun getMovieDetail(
        @Query("api_key") apiKey: String = APIKEY
    ): Call<Movie>

    @GET("103/similar")
    fun getMovieSimilar(
        @Query("api_key") apiKey: String = APIKEY
    ): Call<MovieListSimilar>
}