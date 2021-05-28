package br.marlon.mobile2you.feature.moviedetail.presenter

import br.marlon.mobile2you.feature.moviedetail.data.model.Movie
import br.marlon.mobile2you.feature.moviedetail.data.model.MovieSimilarItem

interface HomeContract {

    interface View {
        fun showLoadingDialog()
        fun hideLoadingDialog()
        fun showError(message: String)
        fun movieDetail(movie: Movie)
        fun movieList(listMovie: List<MovieSimilarItem>)
        fun favoriteButton()

    }

    interface Presenter {
        fun loadMovies()
        fun loadListMovieSimilar()
    }
}