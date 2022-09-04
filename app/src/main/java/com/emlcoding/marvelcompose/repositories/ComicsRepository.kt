package com.emlcoding.marvelcompose.repositories

import com.emlcoding.marvelcompose.models.Comic
import com.emlcoding.marvelcompose.network.api.ApiClient

// Este Repository es un poco distinto porque no se quiere guardar la informacion en caché para estas peticiones
object ComicsRepository {

    suspend fun get(format: Comic.Format): List<Comic> = ApiClient
            .comicsService
            .getComics(0, 20, format.toStringFormat())
            .data
            .results
            .map { it.asComic() }


    suspend fun find(id: Int): Comic = ApiClient
                .comicsService
                .findComic(id)
                .data
                .results
                .first()
                .asComic()

}