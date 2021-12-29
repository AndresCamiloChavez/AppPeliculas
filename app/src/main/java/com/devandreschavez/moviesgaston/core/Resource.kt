package com.devandreschavez.moviesgaston.core

import kotlin.Exception

/**
 * sirve para retornar varios estados
 * el out sirve para identificar que ese datos solo sirve para retornar el generico
 */
sealed class Resource<out T>{
    class Loading<out T>: Resource<T>()
    data class Success<out T>(val data: T):Resource<T>()
    data class Failure(val exception: Exception):Resource<Nothing>()
}