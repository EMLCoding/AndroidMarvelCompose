package com.emlcoding.marvelcompose.ui.screens.characters

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.emlcoding.marvelcompose.MarvelComposeApp
import com.emlcoding.marvelcompose.models.Character
import com.emlcoding.marvelcompose.repositories.CharactersRepository
import com.emlcoding.marvelcompose.ui.common.AppBarOverflowMenu
import com.emlcoding.marvelcompose.ui.common.ArrowBackIcon

@Composable
fun CharactersScreen(onClick: (Character) -> Unit) {
    var charactersState by rememberSaveable { mutableStateOf(emptyList<Character>()) }

    // Se crea un contexto de corrutinas para llamar a peticiones asincronas
    // Pasandole Unit hacemos que solo se ejecute una vez, sino se ejecutaria cada vez que se actualizar la UI.
    // Esto deberia estar en un ViewModel
    LaunchedEffect(Unit) {
        charactersState = CharactersRepository.getCharacters()
    }

    CharactersScreen(characters = charactersState, onClick = onClick)
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CharactersScreen(characters: List<Character>, onClick: (Character) -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Listado de superheroes") }
            )
        }
    ) { padding ->
        LazyVerticalGrid(
            cells = GridCells.Adaptive(180.dp),
            contentPadding = PaddingValues(4.dp),
            modifier = Modifier.padding(padding)
        ) {
            items(characters) {
                CharacterItem(
                    character = it,
                    modifier = Modifier.clickable { onClick(it) }
                )
            }
        }
    }

}

@Composable
fun CharacterItem(character: Character, modifier: Modifier = Modifier) {
    Column(modifier = modifier.padding(8.dp)) {
        Card {
            AsyncImage(
                model = character.thumbnail,
                contentDescription = character.name,
                modifier = Modifier
                    .background(Color.LightGray)
                    .fillMaxWidth()
                    .aspectRatio(1f),
                contentScale = ContentScale.Crop
            )
        }
        
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = character.name,
                style = MaterialTheme.typography.subtitle1,
                maxLines = 3,
                modifier = Modifier
                    .padding(8.dp, 16.dp) // Primero en horizontal y luego en vertical
                    .weight(1f)
            )
            
            //Spacer(modifier = Modifier.weight(1f))
            
            //AppBarOverflowMenu(urls = character.urls, modifier = Modifier.width(4.dp))
            //Icon(Icons.Filled.Add,"", Modifier.size(20.dp))
            AppBarOverflowMenu(urls = character.urls, modifier = Modifier.width(20.dp))
        }
        
    }
}