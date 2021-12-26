package com.devandreschavez.moviesgaston.repository

import com.devandreschavez.moviesgaston.data.model.MovieList


//define los metodos
interface MovieRepository {
    suspend fun getUpcomingMovies(): MovieList
    suspend fun getTopRatedMovies(): MovieList
    suspend fun getPopularMovies(): MovieList
}