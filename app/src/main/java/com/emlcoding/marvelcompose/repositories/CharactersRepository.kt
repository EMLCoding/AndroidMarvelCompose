package com.emlcoding.marvelcompose.repositories

import com.emlcoding.marvelcompose.models.Character
import com.emlcoding.marvelcompose.network.api.ApiClient
import com.emlcoding.marvelcompose.network.models.Result

object CharactersRepository : Repository<Character>() {

    suspend fun get(): Result<List<Character>> = super.get {
        ApiClient
            .charactersService
            .getCharacters(0, 100)
            .data
            .results
            .map { it.asCharacter() }
    }

    suspend fun find(id: Int): Result<Character> = super.find(
        id,
        findActionRemote = {
            ApiClient
                .charactersService
                .findCharacter(id)
                .data
                .results
                .first()
                .asCharacter()
        }
    )
}