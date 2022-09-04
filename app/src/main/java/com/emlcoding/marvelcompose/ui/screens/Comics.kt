package com.emlcoding.marvelcompose.ui.screens

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.emlcoding.marvelcompose.R
import com.emlcoding.marvelcompose.models.Comic
import com.emlcoding.marvelcompose.repositories.ComicsRepository
import com.emlcoding.marvelcompose.ui.common.MarvelItemDetailScreen
import com.emlcoding.marvelcompose.ui.common.MarvelItemsList
import com.emlcoding.marvelcompose.ui.common.MarvelItemsListScreen
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalPagerApi::class)
@Composable
fun ComicsScreen(onClick: (Comic) -> Unit) {
    var comicsState by remember() { mutableStateOf(emptyList<Comic>()) }

    LaunchedEffect(Unit) {
        comicsState = ComicsRepository.get()
    }

    val formats = Comic.Format.values().toList()
    val pagerState = rememberPagerState()


    Column {
        ComicFormatsTabRow(pagerState, formats)

        HorizontalPager(
            count = formats.size,
            state = pagerState
        ) {
            MarvelItemsList(loading = false, items = comicsState, onClick = onClick)
        }
    }

}

@OptIn(ExperimentalPagerApi::class)
@Composable
private fun ComicFormatsTabRow(
    pagerState: PagerState,
    formats: List<Comic.Format>
) {
    val scope = rememberCoroutineScope()
    // Por defecto el ScrollableTabRow deja un hueco a la izquierda por si se quiere insertar un icono. Para quitar este hueco hay que usar el edgePadding
    ScrollableTabRow(selectedTabIndex = pagerState.currentPage, edgePadding = 0.dp) {
        formats.forEach {
            Tab(
                selected = it.ordinal == pagerState.currentPage,
                onClick = { scope.launch { pagerState.animateScrollToPage(it.ordinal) } },
                text = { Text(text = stringResource(id = it.toStringRes())) })
        }
    }
}

@StringRes
private fun Comic.Format.toStringRes(): Int = when(this) {
    Comic.Format.COMIC -> R.string.comic
    Comic.Format.MAGAZINE -> R.string.magazine
    Comic.Format.TRADE_PAPERBACK -> R.string.trade_paperback
    Comic.Format.HARDCOVER -> R.string.hardcover
    Comic.Format.DIGEST -> R.string.digest
    Comic.Format.GRAPHIC_NOVEL -> R.string.graphic_novel
    Comic.Format.DIGITAL_COMIC -> R.string.digital_comic
    Comic.Format.INFINITE_COMIC -> R.string.infinite_comic
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