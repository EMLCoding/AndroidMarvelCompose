package com.emlcoding.marvelcompose.repositories

import com.emlcoding.marvelcompose.models.Comic
import com.emlcoding.marvelcompose.network.api.ApiClient
import com.emlcoding.marvelcompose.network.models.Result
import com.emlcoding.marvelcompose.network.models.tryCall

// Este Repository es un poco distinto porque no se quiere guardar la informacion en caché para estas peticiones
object ComicsRepository {

    suspend fun get(format: Comic.Format): Result<List<Comic>> = tryCall {
        ApiClient
            .comicsService
            .getComics(0, 20, format.toStringFormat())
            .data
            .results
            .map { it.asComic() }
    }


    suspend fun find(id: Int): Result<Comic> = tryCall {
        ApiClient
            .comicsService
            .findComic(id)
            .data
            .results
            .first()
            .asComic()
    }

}