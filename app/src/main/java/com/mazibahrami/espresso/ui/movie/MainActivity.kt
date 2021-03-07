package com.mazibahrami.espresso.ui.movie

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.request.RequestOptions
import com.mazibahrami.espresso.R
import com.mazibahrami.espresso.data.source.MoviesDataSource
import com.mazibahrami.espresso.data.source.MoviesRemoteDataSource
import com.mazibahrami.espresso.databinding.ActivityMainBinding
import com.mazibahrami.espresso.factory.MovieFragmentFactory
import com.mazibahrami.espresso.ui.UICommunicationListener

class MainActivity : AppCompatActivity(), UICommunicationListener {
    private lateinit var binding: ActivityMainBinding

    // dependencies (typically would be injected with dagger)
    lateinit var requestOptions: RequestOptions
    lateinit var moviesDataSource: MoviesDataSource

    override fun onCreate(savedInstanceState: Bundle?) {
        initDependencies()
        supportFragmentManager.fragmentFactory = MovieFragmentFactory(
            requestOptions,
            moviesDataSource
        )
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        init()
    }

    private fun init() {
        if (supportFragmentManager.fragments.size == 0) {
            val movieId = 1
            val bundle = Bundle()
            bundle.putInt("movie_id", movieId)
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, MovieListFragment::class.java, bundle)
                .commit()
        }
    }

    private fun initDependencies() {
        // glide
        requestOptions = RequestOptions
            .placeholderOf(R.drawable.default_image)
            .error(R.drawable.default_image)

        // data source
        moviesDataSource = MoviesRemoteDataSource()
    }

    override fun loading(isLoading: Boolean) {
        if (isLoading)
            binding.progressBar.visibility = View.VISIBLE
        else
            binding.progressBar.visibility = View.INVISIBLE
    }

}







