package com.mazibahrami.espresso.ui.movie


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.mazibahrami.espresso.R
import com.mazibahrami.espresso.data.Movie
import com.mazibahrami.espresso.data.source.MoviesDataSource
import com.mazibahrami.espresso.databinding.FragmentMovieListBinding
import com.mazibahrami.espresso.util.TopSpacingItemDecoration

class MovieListFragment(
    private val moviesDataSource: MoviesDataSource
) : Fragment(),
    MoviesListAdapter.Interaction {
    private var _binding: FragmentMovieListBinding? = null
    private val binding get() = _binding!!

    override fun onItemSelected(position: Int, item: Movie) {
        activity?.run {
            val bundle = Bundle()
            bundle.putInt("movie_id", item.id)
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, MovieDetailFragment::class.java, bundle)
                .addToBackStack("MovieDetailFragment")
                .commit()
        }
    }

    lateinit var listAdapter: MoviesListAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMovieListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecyclerView()
        getData()
    }

    private fun getData() {
        listAdapter.submitList(moviesDataSource.getMovies())
    }

    private fun initRecyclerView() {
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(activity)
            removeItemDecoration(TopSpacingItemDecoration(30))
            addItemDecoration(TopSpacingItemDecoration(30))
            listAdapter = MoviesListAdapter(this@MovieListFragment)
            adapter = listAdapter
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}