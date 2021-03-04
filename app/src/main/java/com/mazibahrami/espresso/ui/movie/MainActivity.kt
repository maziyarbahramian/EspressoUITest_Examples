package com.mazibahrami.espresso.ui.movie

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.mazibahrami.espresso.R
import com.mazibahrami.espresso.databinding.ActivityMainBinding
import com.mazibahrami.espresso.factory.MovieFragmentFactory

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        supportFragmentManager.fragmentFactory = MovieFragmentFactory()
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
                .replace(R.id.container, MovieDetailFragment::class.java, bundle)
                .commit()
        }
    }

}







