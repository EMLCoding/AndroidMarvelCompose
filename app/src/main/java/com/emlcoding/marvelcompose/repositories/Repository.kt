package com.emlcoding.marvelcompose.repositories

import arrow.core.Either
import arrow.core.left
import arrow.core.right
import com.emlcoding.marvelcompose.models.MarvelItem
import com.emlcoding.marvelcompose.network.models.Result
import com.emlcoding.marvelcompose.network.models.tryCall
import java.lang.Exception

abstract class Repository<T: MarvelItem> {

    // Se va a cachear las peticiones para que no este todo el rato consultando al servidor
    private var cache: List<T> = emptyList()

    internal suspend fun get(getAction: suspend () -> List<T>): Result<List<T>> = tryCall {
        if (cache.isEmpty()) {
            cache = getAction()
        }
        cache
    }

    internal suspend fun find(
        id: Int,
        findActionRemote: suspend () -> T
    ): Result<T> = tryCall {
        val item = cache.find { it.id == id }

        item ?: findActionRemote()
    }
}