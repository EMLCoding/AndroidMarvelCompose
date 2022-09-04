package com.emlcoding.marvelcompose.viewModels.comics

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.emlcoding.marvelcompose.models.Comic
import com.emlcoding.marvelcompose.repositories.ComicsRepository
import kotlinx.coroutines.launch

// Este viewModel es un poco distinto a los otros dos porque se hace una peticion, y se genera un state, por cada pestaña de la pantalla de Comics
class ComicsViewModel: ViewModel() {

    val state = Comic.Format.values().associateWith { mutableStateOf(UiState()) }

    data class UiState(
        val loading: Boolean = false,
        val items: List<Comic> = emptyList()
    )

    fun formatRequested(format: Comic.Format) {
        val uiState = state.getValue(format)
        if (uiState.value.items.isNotEmpty()) return

        viewModelScope.launch {
            uiState.value = UiState(loading = true)
            uiState.value = UiState(items = ComicsRepository.get(format))
        }
    }
}