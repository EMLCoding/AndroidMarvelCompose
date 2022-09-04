package com.emlcoding.marvelcompose.ui.screens

import androidx.compose.runtime.*
import com.emlcoding.marvelcompose.models.Character
import com.emlcoding.marvelcompose.repositories.CharactersRepository
import com.emlcoding.marvelcompose.ui.common.MarvelItemDetailScreen
import com.emlcoding.marvelcompose.ui.common.MarvelItemsListScreen
import com.emlcoding.marvelcompose.viewModels.characters.CharactersViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.emlcoding.marvelcompose.viewModels.characters.CharacterDetailViewModel

@Composable
fun CharactersScreen(onClick: (Character) -> Unit, viewModel: CharactersViewModel = viewModel()) {

    MarvelItemsListScreen(
        loading = viewModel.state.loading,
        items = viewModel.state.items,
        onClick = onClick
    )
}


@Composable
fun CharacterDetailScreen(viewModel: CharacterDetailViewModel = viewModel()) {

    MarvelItemDetailScreen(
        loading = viewModel.state.loading,
        marvelItem = viewModel.state.character
    )
}