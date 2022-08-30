package com.emlcoding.marvelcompose.repositories

import com.emlcoding.marvelcompose.models.Character
import com.emlcoding.marvelcompose.models.Reference
import com.emlcoding.marvelcompose.network.models.Character as NetworkCharacter
import com.emlcoding.marvelcompose.network.api.ApiClient
import com.emlcoding.marvelcompose.network.models.asString

object CharactersRepository {

    // Es suspend porque Retrofit es compatible con Corrutinas
    suspend fun getCharacters(): List<Character> {
        val result = ApiClient.charactersService.getCharacters(0, 100)

        return result.data.results.map {
            // Convierte cada objeto Character que viene de la API en un objeto Character de nuestro model
            it.asCharacter()
        }
    }

    suspend fun findCharacter(characterId: Int): Character {
        val result = ApiClient.charactersService.findCharacter(characterId)

        return result.data.results.first().asCharacter()
    }
}

fun NetworkCharacter.asCharacter(): Character {
    val comics = comics.items.map { Reference(it.name) }
    val series = series.items.map { Reference(it.name) }
    val events = events.items.map { Reference(it.name) }
    val stories = stories.items.map { Reference(it.name) }

    return Character(
        id,
        name,
        description,
        thumbnail.asString(),
        comics,
        events,
        stories,
        series,
        urls
    )
}

