package com.emlcoding.marvelcompose.ui.screens.detail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Book
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.Collections
import androidx.compose.material.icons.filled.Event
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.emlcoding.marvelcompose.models.Character
import com.emlcoding.marvelcompose.models.Reference
import com.emlcoding.marvelcompose.repositories.CharactersRepository
import com.emlcoding.marvelcompose.ui.common.Header

@Composable
fun CharacterDetailScreen(characterId: Int) {
    var characterState by rememberSaveable { mutableStateOf<Character?>(null) }
    LaunchedEffect(Unit) {
        characterState = CharactersRepository.findCharacter(characterId)
    }

    // Hasta que el characterState no sea distinto de nulo no entrará
    characterState?.let { character ->
        CharacterDetailScreen(character = character)
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CharacterDetailScreen(character: Character) {
    LazyColumn(modifier = Modifier.fillMaxWidth()) {
        item {
            Header(character = character)
        }

        section(Icons.Default.Collections, "Series", character.series)
        section(Icons.Default.Event, "Events", character.events)
        section(Icons.Default.Book, "Comics", character.comics)
        section(Icons.Default.Bookmark, "Stories", character.stories)
    }
}

@OptIn(ExperimentalMaterialApi::class)
fun LazyListScope.section(icon: ImageVector, name: String, items: List<Reference>) {
    if (items.isEmpty()) {
        return
    }

    item {
        Text(
            text = name,
            style = MaterialTheme.typography.h5,
            modifier = Modifier.padding(16.dp)
        )
    }

    items(items) {
        ListItem(
            icon = { Icon(imageVector = icon, contentDescription = null)},
            text = { Text(it.name) }
        )
    }
}