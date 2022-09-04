package com.emlcoding.marvelcompose.viewModels.characters

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import arrow.core.Either
import com.emlcoding.marvelcompose.models.Character
import com.emlcoding.marvelcompose.navigation.NavArg
import com.emlcoding.marvelcompose.network.models.Result
import com.emlcoding.marvelcompose.repositories.CharactersRepository
import kotlinx.coroutines.launch

// El parametro savedStateHandle se pasa automaticamente y lleva los valores del navigation
class CharacterDetailViewModel(savedStateHandle: SavedStateHandle): ViewModel() {

    private val id = savedStateHandle.get<Int>(NavArg.ItemId.key) ?: -1

    var state by mutableStateOf(UiState())
        private set

    init {
        viewModelScope.launch {
            state = UiState(loading = true)
            state = UiState(character = CharactersRepository.find(id))
        }
    }

    data class UiState(
        val loading: Boolean = false,
        val character: Result<Character?> = Either.Right(null)
    )
}