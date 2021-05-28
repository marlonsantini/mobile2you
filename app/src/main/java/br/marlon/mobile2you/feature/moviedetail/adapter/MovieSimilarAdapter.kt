package br.marlon.mobile2you.feature.moviedetail.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.marlon.mobile2you.databinding.RowMovieSimilarBinding
import br.marlon.mobile2you.feature.moviedetail.data.model.Movie
import br.marlon.mobile2you.feature.moviedetail.data.model.MovieListSimilar
import br.marlon.mobile2you.feature.moviedetail.data.model.MovieSimilarItem
import coil.load

class MovieSimilarAdapter(private val movies: List<MovieSimilarItem>) :
    RecyclerView.Adapter<MovieSimilarAdapter.MovieSimilarHolder>() {

    class MovieSimilarHolder(val binding: RowMovieSimilarBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieSimilarHolder {
        val binding = RowMovieSimilarBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieSimilarHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieSimilarHolder, position: Int) {
        with(holder) {
            with(movies[position]) {
                binding.tvTitle.text = title
                binding.tvYear.text = releaseDate
                binding.imgMovieSimilar.load(posterPath)
            }
        }
    }

    override fun getItemCount() = movies.size


}