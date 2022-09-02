package com.emlcoding.marvelcompose.ui.screens

import androidx.compose.runtime.*
import com.emlcoding.marvelcompose.models.Comic
import com.emlcoding.marvelcompose.repositories.ComicsRepository
import com.emlcoding.marvelcompose.ui.common.MarvelItemDetailScreen
import com.emlcoding.marvelcompose.ui.common.MarvelItemsListScreen

@Composable
fun ComicsScreen(onClick: (Comic) -> Unit) {
    var comicsState by remember() { mutableStateOf(emptyList<Comic>()) }

    LaunchedEffect(Unit) {
        comicsState = ComicsRepository.get()
    }

    MarvelItemsListScreen(
        items = comicsState,
        onClick = onClick
    )
}

@Composable
fun ComicDetailScreen(comicId: Int, onUpClick: () -> Unit) {
    var comicState by remember { mutableStateOf<Comic?>(null) }

    LaunchedEffect(Unit) {
        comicState = ComicsRepository.find(comicId)
    }

    comicState?.let {
        MarvelItemDetailScreen(marvelItem = it, onUpClick = onUpClick)
    }
}