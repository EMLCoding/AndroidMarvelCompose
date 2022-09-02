package com.emlcoding.marvelcompose.repositories

import com.emlcoding.marvelcompose.models.MarvelItem

abstract class Repository<T: MarvelItem> {

    // Se va a cachear las peticiones para que no este todo el rato consultando al servidor
    private var cache: List<T> = emptyList()

    internal suspend fun get(getAction: suspend () -> List<T>): List<T> {
        if (cache.isEmpty()) {
            cache = getAction()
        }
        return cache
    }

    internal suspend fun find(
        id: Int,
        findActionRemote: suspend () -> T
    ): T {
        val item = cache.find { it.id == id }
        if (item != null) return item

        return findActionRemote()
    }
}