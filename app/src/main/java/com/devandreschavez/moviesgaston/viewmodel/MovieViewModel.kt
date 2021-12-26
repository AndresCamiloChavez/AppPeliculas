package com.devandreschavez.moviesgaston.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.liveData
import com.devandreschavez.moviesgaston.core.Resource
import com.devandreschavez.moviesgaston.repository.MovieRepository
import kotlinx.coroutines.Dispatchers

class MovieViewModel(private val repo: MovieRepository): ViewModel(){

    fun fetchMainScreenMovies() = liveData(Dispatchers.IO) {
        emit(Resource.Loading())
        try {
            //para ejecutar todas las llamadas al mismo tiempo
            emit(Resource.Success(Triple(repo.getUpcomingMovies(), repo.getTopRatedMovies(), repo.getPopularMovies())))
        }catch (e: Exception){
            emit(Resource.Failure(e))
        }
    }
//    fun fetchUpcomingMovies() = liveData(Dispatchers.IO){
//        emit(Resource.Loading())
//        try {
//            emit(Resource.Success(repo.getUpcomingMovies()))
//        }catch (e: Exception){
//            emit(Resource.Failure(e))
//        }
//    }
//    fun fetchTopRatedMovies() = liveData(Dispatchers.IO){
//        emit(Resource.Loading())
//        try {
//            emit(Resource.Success(repo.getTopRatedMovies()))
//        }catch (e: Exception){
//            emit(Resource.Failure(e))
//        }
//    }
//    fun fetchPopularMovies() = liveData(Dispatchers.IO){
//        emit(Resource.Loading())
//        try {
//            emit(Resource.Success(repo.getPopularMovies()))
//        }catch (e: Exception){
//            emit(Resource.Failure(e))
//        }
//    }
}
class MovieViewModelFactory(private val repo: MovieRepository): ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(MovieRepository::class.java).newInstance(repo)
    }

}
//n llamadas
//data class NTuple5<T1,T2,T3,T4,T5>(val t1: T1,val t2: T2,val t3: T3,val t4: T4,val t5: T5)