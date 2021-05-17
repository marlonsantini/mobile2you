package br.marlon.mobile2you.feature.moviedetail.data.model

import com.google.gson.annotations.SerializedName

data class MovieListSimilar(
    val results: List<MovieSimilarItem>
    )

data class MovieSimilarItem (
    @SerializedName("title")
    val title: String,
    @SerializedName("poster_path")
    val posterPath: String,
    @SerializedName("release_date")
    val releaseDate: String
    )