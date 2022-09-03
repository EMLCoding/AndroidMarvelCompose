package com.emlcoding.marvelcompose.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.emlcoding.marvelcompose.R
import com.emlcoding.marvelcompose.models.Comic
import com.emlcoding.marvelcompose.repositories.ComicsRepository
import com.emlcoding.marvelcompose.ui.common.MarvelItemDetailScreen
import com.emlcoding.marvelcompose.ui.common.MarvelItemsList
import com.emlcoding.marvelcompose.ui.common.MarvelItemsListScreen
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch

@OptIn(ExperimentalPagerApi::class)
@Composable
fun ComicsScreen(onClick: (Comic) -> Unit) {
    var comicsState by remember() { mutableStateOf(emptyList<Comic>()) }

    LaunchedEffect(Unit) {
        comicsState = ComicsRepository.get()
    }

    val formats = Comic.Format.values().take(3)
    val pagerState = rememberPagerState()
    val scope = rememberCoroutineScope()

    Column {
        TabRow(selectedTabIndex = pagerState.currentPage) {
            formats.forEach {
                Tab(
                    selected = it.ordinal == pagerState.currentPage,
                    onClick = { scope.launch { pagerState.animateScrollToPage(it.ordinal) } },
                    text = { Text(text = it.name) })
            }
        }

        HorizontalPager(
            count = formats.size,
            state = pagerState
        ) {
            MarvelItemsList(items = comicsState, onClick = onClick)
        }
    }

}

@Composable
fun ComicDetailScreen(comicId: Int) {
    var comicState by remember { mutableStateOf<Comic?>(null) }

    LaunchedEffect(Unit) {
        comicState = ComicsRepository.find(comicId)
    }

    comicState?.let {
        MarvelItemDetailScreen(marvelItem = it)
    }
}