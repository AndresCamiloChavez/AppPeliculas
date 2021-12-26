package com.devandreschavez.moviesgaston.data.remote

import com.devandreschavez.moviesgaston.application.AppConstants
import com.devandreschavez.moviesgaston.data.model.MovieList
import com.devandreschavez.moviesgaston.repository.WebService

class MovieDataSource(private val webService: WebService) {
    suspend fun getUpcomingMovies(): MovieList = webService.getUpcomingMovies(AppConstants.API_KEY,"es-CO")
    suspend fun getTopRatedMovies(): MovieList = webService.getTopRatedMovies(AppConstants.API_KEY,"es-CO")
    suspend fun getPopularMovies(): MovieList = webService.getPopularMovies(AppConstants.API_KEY,"es-CO")
}