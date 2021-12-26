package com.devandreschavez.moviesgaston.ui.details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.devandreschavez.moviesgaston.R
import com.devandreschavez.moviesgaston.databinding.FragmentMovieDetailBinding

class MovieDetailFragment : Fragment(R.layout.fragment_movie_detail) {

    private lateinit var binding: FragmentMovieDetailBinding
    private val args:MovieDetailFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMovieDetailBinding.bind(view)

        Glide.with(requireContext())
            .load("https://image.tmdb.org/t/p/w500${args.movie.backdrop_path}").centerCrop()
            .into(binding.imgBackground)
        Glide.with(requireContext())
            .load("https://image.tmdb.org/t/p/w500${args.movie.poster_path}").centerCrop()
            .into(binding.imgMovie)
        binding.apply {
            tvRating.text = "${args.movie.vote_average} ${args.movie.vote_count} Reviews"
            tvDescription.text = args.movie.overview
            tvLanguaje.text = "Languaje ${args.movie.original_language}"
            tvReleased.text = "Released ${args.movie.release_date}"
            tvTitleMovie.text = args.movie.title
        }

    }
}