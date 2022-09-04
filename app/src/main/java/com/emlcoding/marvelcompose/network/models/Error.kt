package com.emlcoding.marvelcompose.network.models

import arrow.core.Either
import arrow.core.left
import arrow.core.right
import retrofit2.HttpException
import java.io.IOException
import java.lang.Exception

typealias Result<T> = Either<Error, T>

sealed class Error {
    class Server(val code: Int): Error()
    object Connectivity: Error()
    class Unknown(val message: String): Error()
}

fun Exception.toError() : Error = when(this) {
    is IOException -> Error.Connectivity
    is HttpException -> Error.Server(code())
    else -> Error.Unknown(message ?: "")
}

// EL inline hace que el compilador sustituya cada llamada a esta funcion por el propio codigo de la funcion
inline fun <T> tryCall(action: () -> T): Result<T> = try {
    // El right es porque se lanza por la parte derecha del Either, que es el del elemento
    action().right()
} catch (e: Exception) {
    // El left es porque se lanza por la parte izquierda del Either, que es el de errores
    e.toError().left()
}
