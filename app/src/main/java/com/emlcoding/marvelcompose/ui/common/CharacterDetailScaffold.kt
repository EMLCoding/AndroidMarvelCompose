package com.emlcoding.marvelcompose.ui.common

import android.content.Context
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Share
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
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
            if (character.urls.isNotEmpty()) {
                FloatingActionButton(onClick = { shareCharacter(context, character) }) {
                    Icon(imageVector = Icons.Default.Share, contentDescription = stringResource(id = R.string.share_character))
                }
            }
        },
        // Por defecto el FloatingActionButton se pone a la derecha, pero con la siguiente linea se podria poner en el centro
        floatingActionButtonPosition = FabPosition.Center,
        isFloatingActionButtonDocked = true, // Esto, junto al "cutoutShape" de la bottomBar permite hacer la integracion de los dos componentes visualmente. Esta propiedad por defecto esta a false
        bottomBar = {
                  BottomAppBar(
                      cutoutShape = CircleShape // Aqui hay que elegir la misma forma que tenga el FloatingActionButton para poder hacer el efecto visual. Esta propiedad en concreto es la que hace el efecto de recorte
                  ) {

                      AppBarIcon(imageVector = Icons.Default.Menu, onClick = { /*TODO*/ })
                      
                      Spacer(modifier = Modifier.weight(1f))

                      AppBarIcon(imageVector = Icons.Default.Favorite, onClick = { /*TODO*/ })
                  }
        },
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
