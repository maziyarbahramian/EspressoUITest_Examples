package com.mazibahrami.espresso.data.source

import com.mazibahrami.espresso.data.FakeMovieData
import com.mazibahrami.espresso.data.Movie

class MoviesRemoteDataSource : MoviesDataSource {

    private var movieRemoteData = LinkedHashMap<Int, Movie>(2)

    init {
        for (movie in FakeMovieData.movies) {
            addMovie(movie)
        }
    }

    override fun getMovie(movieId: Int): Movie? {
        return movieRemoteData[movieId]
    }

    override fun getMovies(): List<Movie> {
        return ArrayList(movieRemoteData.values)
    }

    private fun addMovie(movie: Movie) {
        movieRemoteData[movie.id] = movie
    }


}














