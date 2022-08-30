package com.emlcoding.marvelcompose.ui.common

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalUriHandler
import com.emlcoding.marvelcompose.network.models.Url

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun AppBarOverflowMenu(urls: List<Url>, modifier: Modifier = Modifier) {
    var showMenu by remember { mutableStateOf(false) }
    // Es una forma de elementos implicitos
    val uriHandler = LocalUriHandler.current

    if (urls.isEmpty()) return

    IconButton(onClick = { showMenu = !showMenu }, modifier = modifier) {
        Icon(imageVector = Icons.Default.MoreVert, contentDescription = "More actions")
    }
    DropdownMenu(expanded = showMenu, onDismissRequest = { showMenu = false }) {
        urls.forEach {
            DropdownMenuItem(onClick = {
                uriHandler.openUri(it.url)
                showMenu = false
            }) {
                ListItem(text = { Text(it.type) })
            }
        }
    }
}