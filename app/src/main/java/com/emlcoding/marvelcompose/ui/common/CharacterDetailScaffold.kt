package com.emlcoding.marvelcompose.ui.common

import android.content.Context
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Share
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.core.app.ShareCompat
import com.emlcoding.marvelcompose.R
import com.emlcoding.marvelcompose.models.Character

@Composable
fun CharacterDetailScaffold(
    character: Character,
    onUpClick: () -> Unit,
    content: @Composable (PaddingValues) -> Unit
) {
    val context = LocalContext.current
    Scaffold(
        topBar = {
            TopAppBar (
                title = { Text(character.name) },
                navigationIcon = { ArrowBackIcon(onUpClick) },
                actions = { AppBarOverflowMenu(urls = character.urls) }
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { shareCharacter(context, character) }) {
                Icon(imageVector = Icons.Default.Share, contentDescription = stringResource(id = R.string.share_character))
            }
        },
        // Por defecto el FloatingActionButton se pone a la derecha, pero con la siguiente linea se podria poner en el centro
        floatingActionButtonPosition = FabPosition.Center,
        content = content
    )
}

// Funcion que permite compartir un elemento en otras apps
fun shareCharacter(context: Context, character: Character) {
    ShareCompat
        .IntentBuilder(context)
        .setType("text/plain")
        .setSubject(character.name)
        .setText(character.urls.first().url)
        .intent
        .also(context::startActivity)
}
