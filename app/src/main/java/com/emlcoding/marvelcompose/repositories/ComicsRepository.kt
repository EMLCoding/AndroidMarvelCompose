package com.emlcoding.marvelcompose.repositories

import com.emlcoding.marvelcompose.models.Comic
import com.emlcoding.marvelcompose.network.api.ApiClient

object ComicsRepository: Repository<Comic>() {

    suspend fun get(format: Comic.Format? = null): List<Comic> = super.get {
        ApiClient
            .comicsService
            .getComics(0, 100, format?.toStringFormat())
            .data
            .results
            .map { it.asComic() }
    }

    suspend fun find(id: Int): Comic = super.find(
        id,
        findActionRemote = {
            ApiClient
                .comicsService
                .findComic(id)
                .data
                .results
                .first()
                .asComic()
        }
    )
}