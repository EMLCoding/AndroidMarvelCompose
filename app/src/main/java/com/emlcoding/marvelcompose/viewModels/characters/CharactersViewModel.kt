package com.emlcoding.marvelcompose.viewModels.characters

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import arrow.core.right
import com.emlcoding.marvelcompose.models.Character
import com.emlcoding.marvelcompose.network.models.Result
import com.emlcoding.marvelcompose.repositories.CharactersRepository
import kotlinx.coroutines.launch

class CharactersViewModel: ViewModel() {

    // Con estas dos lineas se esta haciendo que el metodo get del state sea publico, pero el set sea privado
    var state by mutableStateOf(UiState())
        private set

    init {
        // Permite lanzar corutinas
        viewModelScope.launch {
            state = UiState(loading = true)
            state = UiState(items = CharactersRepository.get())
        }
    }

    data class UiState(
        val loading: Boolean = false,
        val items: Result<List<Character>> = emptyList<Character>().right()
    )
}