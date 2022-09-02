package com.emlcoding.marvelcompose.repositories

import com.emlcoding.marvelcompose.models.Character
import com.emlcoding.marvelcompose.network.api.ApiClient

object CharactersRepository : Repository<Character>() {

    suspend fun get(): List<Character> = super.get {
        ApiClient
            .charactersService
            .getCharacters(0, 100)
            .data
            .results
            .map { it.asCharacter() }
    }

    suspend fun find(id: Int): Character = super.find(
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