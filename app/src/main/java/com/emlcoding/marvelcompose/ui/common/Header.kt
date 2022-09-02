package com.emlcoding.marvelcompose.ui.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.emlcoding.marvelcompose.models.Character
import com.emlcoding.marvelcompose.models.MarvelItem

@Composable
fun Header(marvelItem: MarvelItem) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        AsyncImage(
            model = marvelItem.thumbnail,
            contentDescription = marvelItem.title,
            modifier = Modifier
                .background(Color.LightGray)
                .fillMaxWidth()
                .aspectRatio(1f),
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = marvelItem.title,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.h4,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp, 0.dp))

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = marvelItem.description,
            style = MaterialTheme.typography.body1,
            modifier = Modifier
                .padding(16.dp, 0.dp)
        )
    }
}