package com.emlcoding.marvelcompose.ui.screens

import androidx.compose.runtime.*
import com.emlcoding.marvelcompose.models.Event
import com.emlcoding.marvelcompose.repositories.EventsRepository
import com.emlcoding.marvelcompose.ui.common.MarvelItemDetailScreen
import com.emlcoding.marvelcompose.ui.common.MarvelItemsListScreen

@Composable
fun EventsScreen(onClick: (Event) -> Unit) {
    var eventsState by remember() { mutableStateOf(emptyList<Event>()) }
    LaunchedEffect(Unit) {
        eventsState = EventsRepository.get()
    }
    MarvelItemsListScreen(
        items = eventsState,
        onClick = onClick
    )
}

@Composable
fun EventDetailScreen(eventId: Int) {
    var eventState by remember { mutableStateOf<Event?>(null) }
    LaunchedEffect(Unit) {
        eventState = EventsRepository.find(eventId)
    }
    eventState?.let {
        MarvelItemDetailScreen(it)
    }
}