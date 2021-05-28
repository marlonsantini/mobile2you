package br.marlon.mobile2you.feature.moviedetail


import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.marlon.mobile2you.R
import br.marlon.mobile2you.databinding.ActivityHomeModBinding
import br.marlon.mobile2you.feature.moviedetail.adapter.MovieSimilarAdapter
import br.marlon.mobile2you.feature.moviedetail.data.model.Movie
import br.marlon.mobile2you.feature.moviedetail.data.model.MovieSimilarItem
import br.marlon.mobile2you.feature.moviedetail.presenter.HomeContract
import br.marlon.mobile2you.feature.moviedetail.presenter.HomePresenter
import coil.load
import com.google.android.material.snackbar.Snackbar

class HomeActivity : AppCompatActivity(), HomeContract.View {

    private lateinit var binding: ActivityHomeModBinding
    private val presenter = HomePresenter(this)
    private var isPressed = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeModBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        setSupportActionBar(findViewById(R.id.toolbar))

        presenter.loadMovies()
        presenter.loadListMovieSimilar()

        favoriteButton()
    }

    override fun showLoadingDialog() {
        binding.loading.visibility = View.VISIBLE
    }

    override fun hideLoadingDialog() {
        binding.loading.visibility = View.GONE
    }

    override fun showError(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    override fun movieDetail(movie: Movie) {
        binding.imgMovie.load(movie.backdropPath)
        binding.toolbarLayout.title = movie.title
//        binding.tvLikes.text = movie.likes
//        binding.tvPopularity.text = movie.popularity
    }

    override fun movieList(listMovie: List<MovieSimilarItem>) {
        binding.rvSimilarMovies.adapter.apply {
            val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(
                this@HomeActivity,
                LinearLayoutManager.VERTICAL,
                false
            )
            binding.rvSimilarMovies.layoutManager = layoutManager
            binding.rvSimilarMovies.adapter =
                MovieSimilarAdapter(listMovie)
        }
    }

    override fun favoriteButton() {
        binding.favBtn.setOnClickListener {
            if (isPressed) {
                binding.favBtn.setImageResource(R.drawable.ic_fav_off)
                Snackbar.make(binding.root, "Desmarcado como favorito", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
            } else {
                binding.favBtn.setImageResource(R.drawable.ic_fav)
                Snackbar.make(binding.root, "Marcado como favorito", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
            }
            isPressed = !isPressed
        }
    }
}