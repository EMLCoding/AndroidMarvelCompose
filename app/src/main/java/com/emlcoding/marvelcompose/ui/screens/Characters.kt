package com.emlcoding.marvelcompose.ui.screens

import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import com.emlcoding.marvelcompose.models.Character
import com.emlcoding.marvelcompose.repositories.CharactersRepository
import com.emlcoding.marvelcompose.ui.common.MarvelItemDetailScreen
import com.emlcoding.marvelcompose.ui.common.MarvelItemsListScreen

@Composable
fun CharactersScreen(onClick: (Character) -> Unit) {
    var charactersState by rememberSaveable { mutableStateOf(emptyList<Character>()) }

    // Se crea un contexto de corrutinas para llamar a peticiones asincronas
    // Pasandole Unit hacemos que solo se ejecute una vez, sino se ejecutaria cada vez que se actualizar la UI.
    // Esto deberia estar en un ViewModel
    LaunchedEffect(Unit) {
        charactersState = CharactersRepository.get()
    }

    MarvelItemsListScreen(
        items = charactersState,
        onClick = onClick
    )
}


@Composable
fun CharacterDetailScreen(characterId: Int) {
    var characterState by remember { mutableStateOf<Character?>(null) }
    LaunchedEffect(Unit) {
        characterState = CharactersRepository.find(characterId)
    }

    characterState?.let {
        MarvelItemDetailScreen(it)
    }
}