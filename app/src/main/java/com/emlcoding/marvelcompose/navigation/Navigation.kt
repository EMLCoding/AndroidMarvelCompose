package com.emlcoding.marvelcompose.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.emlcoding.marvelcompose.ui.screens.*

@Composable
fun Navigation(navController: NavHostController) {

    NavHost(
        navController = navController,
        startDestination = Feature.CHARACTERS.route
    ) {
        charactersNav(navController)
        comicsNav(navController)
        eventsNav(navController)
        composable(NavCommand.ContentType(Feature.SETTINGS)) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text(text = "Settings", style = MaterialTheme.typography.h3)
            }
        }
    }
}

private fun NavGraphBuilder.charactersNav(navController: NavController) {
    navigation(
        startDestination = NavCommand.ContentType(Feature.CHARACTERS).route,
        route = Feature.CHARACTERS.route
    ) {
        composable(NavCommand.ContentType(Feature.CHARACTERS)) {
            CharactersScreen(
                onClick = { character ->
                    navController.navigate(
                        NavCommand.ContentTypeDetail(Feature.CHARACTERS).createRoute(character.id)
                    )
                }
            )
        }

        composable(NavCommand.ContentTypeDetail(Feature.CHARACTERS)) {
            // Gracias al savedStateHandle: SavedStateHandle del CharacterDetailViewModel no hace falta pasar el id del item a mostrar
            CharacterDetailScreen()
        }
    }
}

private fun NavGraphBuilder.comicsNav(navController: NavController) {
    navigation(
        startDestination = NavCommand.ContentType(Feature.COMICS).route,
        route = Feature.COMICS.route
    ) {
        composable(NavCommand.ContentType(Feature.COMICS)) {
            ComicsScreen(
                onClick = { comic ->
                    navController.navigate(
                        NavCommand.ContentTypeDetail(Feature.COMICS).createRoute(comic.id)
                    )
                }
            )
        }

        composable(NavCommand.ContentTypeDetail(Feature.COMICS)) {
            val id = it.findArg<Int>(NavArg.ItemId)
            ComicDetailScreen(comicId = id)
        }
    }
}

private fun NavGraphBuilder.eventsNav(navController: NavController) {
    navigation(
        startDestination = NavCommand.ContentType(Feature.EVENTS).route,
        route = Feature.EVENTS.route
    ) {
        composable(NavCommand.ContentType(Feature.EVENTS)) {
            EventsScreen(
                onClick = { event ->
                    navController.navigate(
                        NavCommand.ContentTypeDetail(Feature.EVENTS).createRoute(event.id)
                    )
                }
            )
        }

        composable(NavCommand.ContentTypeDetail(Feature.EVENTS)) {
            val id = it.findArg<Int>(NavArg.ItemId)
            EventDetailScreen(eventId = id)
        }
    }
}

private fun NavGraphBuilder.composable(
    navCommand: NavCommand,
    content: @Composable (NavBackStackEntry) -> Unit
) {
    composable(
        route = navCommand.route,
        arguments = navCommand.args
    ) {
        content(it)
    }
}

private inline fun <reified T> NavBackStackEntry.findArg(arg: NavArg): T {
    val value = arguments?.get(arg.key)
    requireNotNull(value)
    return value as T
}