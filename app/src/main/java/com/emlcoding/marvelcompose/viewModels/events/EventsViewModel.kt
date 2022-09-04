package com.emlcoding.marvelcompose.viewModels.events

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.emlcoding.marvelcompose.models.Event
import com.emlcoding.marvelcompose.repositories.EventsRepository
import kotlinx.coroutines.launch

class EventsViewModel: ViewModel() {

    var state by mutableStateOf(UiState())
        private set

    init {
        viewModelScope.launch {
            state = UiState(loading = true)
            // Cuando se cargan los datos de los eventos, se actualizan los estados, y como el estado por defecto del loading es false, por eso desaparece automaticamente
            state = UiState(events = EventsRepository.get())
        }
    }

    data class UiState(
        val loading: Boolean = false,
        val events: List<Event> = emptyList()
    )
}