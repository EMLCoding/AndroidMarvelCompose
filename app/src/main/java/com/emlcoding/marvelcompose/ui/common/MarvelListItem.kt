package com.emlcoding.marvelcompose.ui.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.compose.rememberImagePainter
import com.emlcoding.marvelcompose.models.MarvelItem
import androidx.compose.material.Text

@Composable
fun MarvelListItem(marvelItem: MarvelItem, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.padding(8.dp)
    ) {
        Card {
            AsyncImage(
                model = marvelItem.thumbnail,
                contentDescription = marvelItem.title,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.LightGray)
                    .aspectRatio(1f)
            )
        }
        Box(
            modifier = Modifier.padding(8.dp, 16.dp)
        ) {
            Text(
                text = marvelItem.title,
                style = MaterialTheme.typography.subtitle1,
                maxLines = 2
            )
        }
    }
}