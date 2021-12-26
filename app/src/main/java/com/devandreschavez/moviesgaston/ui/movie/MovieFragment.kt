package com.devandreschavez.moviesgaston.ui.movie

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ConcatAdapter
import com.devandreschavez.moviesgaston.R
import com.devandreschavez.moviesgaston.core.Resource
import com.devandreschavez.moviesgaston.data.model.Movie
import com.devandreschavez.moviesgaston.data.remote.MovieDataSource
import com.devandreschavez.moviesgaston.databinding.FragmentMovieBinding
import com.devandreschavez.moviesgaston.repository.MovieRepositoryImpl
import com.devandreschavez.moviesgaston.repository.RetrofitClient
import com.devandreschavez.moviesgaston.ui.movie.adapters.concat.MovieAdapter
import com.devandreschavez.moviesgaston.ui.movie.adapters.concat.PopularConcatAdapter
import com.devandreschavez.moviesgaston.ui.movie.adapters.concat.TopRatedConcatAdapter
import com.devandreschavez.moviesgaston.ui.movie.adapters.concat.UpcomingConcatAdapter
import com.devandreschavez.moviesgaston.viewmodel.MovieViewModel
import com.devandreschavez.moviesgaston.viewmodel.MovieViewModelFactory

//inflando la view directamente
class MovieFragment : Fragment(R.layout.fragment_movie), MovieAdapter.onMovieClickListener{
    private lateinit var binding: FragmentMovieBinding
    //by -> le doy (delego) un trabajo para que lo haga por nosotros

    private lateinit var concatAdapter: ConcatAdapter

    private val viewModel by viewModels<MovieViewModel> { MovieViewModelFactory(MovieRepositoryImpl(
        MovieDataSource(RetrofitClient.webservice)
    ))}
    //Injección de dependencias manual

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMovieBinding.bind(view)
        concatAdapter = ConcatAdapter()
        //viewLifecycleOwner para no crear multiples observables y solo tener un solo
        viewModel.fetchMainScreenMovies().observe(viewLifecycleOwner, Observer {result ->
            when(result){
                is Resource.Loading -> {
                    Log.d("LiveData", "Loading... ")
                    binding.progressMovie.visibility = View.VISIBLE
                }
                is Resource.Success -> {
                    binding.progressMovie.visibility = View.GONE
                    Log.d("LiveData", "Success Upcoming${result.data.first} \n" +
                            " Top rated ${result.data.second}  Popular ${result.data.third}   ")

                    concatAdapter.apply {
                        addAdapter(0,UpcomingConcatAdapter(MovieAdapter(result.data.first.results, this@MovieFragment)))
                        addAdapter(1,TopRatedConcatAdapter(MovieAdapter(result.data.second.results, this@MovieFragment)))
                        addAdapter(2,PopularConcatAdapter(MovieAdapter(result.data.third.results, this@MovieFragment)))
                    }
                    binding.rvMovies.adapter = concatAdapter
                }
                is Resource.Failure -> {
                    binding.progressMovie.visibility = View.GONE

                    Log.d("LiveData", "Error ${result.exception}")
                    Toast.makeText(activity,"Error", Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

    override fun onMovieClick(movie: Movie) {
        val action = MovieFragmentDirections.actionMovieFragmentToMovieDetailFragment(movie)
        findNavController().navigate(action)
    }
}