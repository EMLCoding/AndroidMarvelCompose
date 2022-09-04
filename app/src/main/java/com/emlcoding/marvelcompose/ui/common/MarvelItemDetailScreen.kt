package com.emlcoding.marvelcompose.ui.common

import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.emlcoding.marvelcompose.R
import com.emlcoding.marvelcompose.models.MarvelItem
import com.emlcoding.marvelcompose.models.Reference
import com.emlcoding.marvelcompose.models.ReferenceList
import com.emlcoding.marvelcompose.network.models.Error
import com.emlcoding.marvelcompose.network.models.Result

@Composable
fun MarvelItemDetailScreen(
    loading: Boolean = false,
    marvelItem: Result<MarvelItem?>
) {

    if (loading) {
        CircularProgressIndicator()
    }
    
    marvelItem.fold({ ErrorMessage(error = it)}) { item ->
        if (item != null) {
            MarvelItemDetailScaffold(marvelItem = item) {
                MarvelItemDetailScaffold(marvelItem = item) { padding ->
                    LazyColumn(modifier = Modifier
                        .fillMaxWidth()
                        .padding(padding)) {
                        item {
                            Header(marvelItem = item)
                        }
                        item.references.forEach {
                            val (icon, @StringRes stringRes) = it.type.createUiData()
                            section(icon, stringRes, it.references)
                        }
                    }
                }
            }
        }
    }

    
}

@OptIn(ExperimentalMaterialApi::class)
private fun LazyListScope.section(icon: ImageVector, @StringRes name: Int, items: List<Reference>) {
    if (items.isEmpty()) return

    item {
        Text(
            text = stringResource(name),
            style = MaterialTheme.typography.h5,
            modifier = Modifier.padding(16.dp)
        )
    }
    items(items) {
        ListItem(
            icon = { Icon(icon, contentDescription = null) },
            text = { Text(it.name) }
        )
    }
}

private fun ReferenceList.Type.createUiData(): Pair<ImageVector, Int> = when (this) {
    ReferenceList.Type.CHARACTER -> Icons.Default.Person to R.string.characters
    ReferenceList.Type.COMIC -> Icons.Default.Book to R.string.comics
    ReferenceList.Type.STORY -> Icons.Default.Bookmark to R.string.stories
    ReferenceList.Type.EVENT -> Icons.Default.Event to R.string.events
    ReferenceList.Type.SERIES -> Icons.Default.Collections to R.string.series
}