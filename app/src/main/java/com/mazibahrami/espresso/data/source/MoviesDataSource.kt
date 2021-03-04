package com.mazibahrami.espresso.data.source

import com.mazibahrami.espresso.data.Movie

interface MoviesDataSource {

    fun getMovie(movieId: Int): Movie?
}