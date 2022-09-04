package com.emlcoding.marvelcompose.ui.screens

import androidx.compose.runtime.*
import com.emlcoding.marvelcompose.models.Event
import com.emlcoding.marvelcompose.ui.common.MarvelItemDetailScreen
import com.emlcoding.marvelcompose.ui.common.MarvelItemsListScreen
import com.emlcoding.marvelcompose.viewModels.events.EventsViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.emlcoding.marvelcompose.viewModels.events.EventsDetailViewModel

@Composable
fun EventsScreen(onClick: (Event) -> Unit, viewModel: EventsViewModel = viewModel()) {

    MarvelItemsListScreen(
        loading = viewModel.state.loading,
        items = viewModel.state.events,
        onClick = onClick
    )
}

@Composable
fun EventDetailScreen(viewModel: EventsDetailViewModel = viewModel()) {
    MarvelItemDetailScreen(
        loading = viewModel.state.loading,
        marvelItem = viewModel.state.event
    )
}