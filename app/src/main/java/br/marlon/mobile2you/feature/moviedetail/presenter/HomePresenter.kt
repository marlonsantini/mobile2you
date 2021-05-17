package br.marlon.mobile2you.feature.moviedetail.presenter

import br.marlon.mobile2you.feature.moviedetail.data.model.Movie
import br.marlon.mobile2you.feature.moviedetail.data.model.MovieListSimilar
import br.marlon.mobile2you.network.service.RetrofitInitializer
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

private const val BASE_URL_IMAGE_W500 = "https://image.tmdb.org/t/p/w500/"
private const val BASE_URL_IMAGE = "https://image.tmdb.org/t/p/original/"


class HomePresenter : HomeContract.Presenter {

    lateinit var view: HomeContract.View

    override fun carregarFilme() {
        view.showLoadingDialog()
        RetrofitInitializer.instance.getMovieDetail()
            .enqueue(object : Callback<Movie> {
                override fun onResponse(call: Call<Movie>, response: Response<Movie>) {
                    if (response.isSuccessful) {
                        // Body não nulo
                        response.body()?.let {
                            val newMovie = it.copy(
                                backdropPath = BASE_URL_IMAGE_W500 + it.backdropPath,
                                popularity = it.popularity + " %"
                            )
                            view.movieDetail(newMovie)
                            view.hideLoadingDialog()
                        }
                    }
                }

                override fun onFailure(call: Call<Movie>, t: Throwable) {
                    view.showError(t.message ?: "")
                    view.hideLoadingDialog()
                }
            })
    }

    override fun carregarListaFilmeSimilar() {
        RetrofitInitializer.instance.getMovieSimilar()
            .enqueue(object : Callback<MovieListSimilar> {
                override fun onResponse(
                    call: Call<MovieListSimilar>,
                    response: Response<MovieListSimilar>
                ) {
                    if (response.isSuccessful) {
                        response.body()?.let {
                            val newListMovie = it.results.map { movieItem ->
                                movieItem.copy(
                                    posterPath = BASE_URL_IMAGE_W500 + movieItem.posterPath
                                )
                            }
                            view.movieList(newListMovie)

                        }
                    }
                }

                override fun onFailure(call: Call<MovieListSimilar>, t: Throwable) {
                    TODO("Not yet implemented")
                }
            })
    }


}