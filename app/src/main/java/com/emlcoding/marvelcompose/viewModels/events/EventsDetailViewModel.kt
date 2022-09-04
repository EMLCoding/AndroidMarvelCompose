package com.emlcoding.marvelcompose.viewModels.events

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.emlcoding.marvelcompose.models.Event
import com.emlcoding.marvelcompose.navigation.NavArg
import com.emlcoding.marvelcompose.repositories.EventsRepository
import kotlinx.coroutines.launch

class EventsDetailViewModel(savedStateHandle: SavedStateHandle): ViewModel() {

    private val id = savedStateHandle.get<Int>(NavArg.ItemId.key) ?: -1

    var state by mutableStateOf(UiState())
        private set

    init {
        viewModelScope.launch {
            state = UiState(loading = true)
            state = UiState(event = EventsRepository.find(id))
        }
    }

    data class UiState(
        val loading: Boolean = false,
        val event: Event? = null
    )
}