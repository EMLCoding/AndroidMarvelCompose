package com.emlcoding.marvelcompose.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.emlcoding.marvelcompose.ui.screens.characters.CharactersScreen
import com.emlcoding.marvelcompose.ui.screens.detail.CharacterDetailScreen
/*

@Composable
fun Navigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = NavItem.Characters.route
    ) {
        composable(NavItem.Characters) {
            CharacterScreen(onClick = { character ->
                navController.navigate(NavItem.CharacterDetail.createRoute(character.id))
            })
        }

        composable(NavItem.CharacterDetail) {
            val id = it.findArg<Int>(NavArg.ItemId)
            CharacterDetailScreen(id)
        }
    }
}

// Es una funcion de extension
private fun NavGraphBuilder.composable(
    navItem: NavItem,
    content: @Composable (NavBackStackEntry) -> Unit
) {
    composable(
        route = navItem.route,
        arguments = navItem.args
    ) {
        content(it)
    }
}

private inline fun <reified T> NavBackStackEntry.findArg(arg: NavArg): T {
    val value = arguments?.get(arg.key)
    // Desde este punto el id se trata como valor no opcional
    requireNotNull(value)
    return value as T
}*/

@Composable
fun Navigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = NavItem.Characters.route
    ) {
        charactersNav(navController)
    }
}

private fun NavGraphBuilder.charactersNav(
    navController: NavController
) {
    composable(NavItem.Characters) {
        CharactersScreen(
            onClick = { character ->
                navController.navigate(NavItem.CharacterDetail.createRoute(character.id))
            }
        )
    }

    composable(NavItem.CharacterDetail) {
        val id = it.findArg<Int>(NavArg.ItemId)
        CharacterDetailScreen(id)
    }
}

private fun NavGraphBuilder.composable(
    navItem: NavItem,
    content: @Composable (NavBackStackEntry) -> Unit
) {
    composable(
        route = navItem.route,
        arguments = navItem.args
    ) {
        content(it)
    }
}

private inline fun <reified T> NavBackStackEntry.findArg(arg: NavArg): T {
    val value = arguments?.get(arg.key)
    requireNotNull(value)
    return value as T
}