package com.emlcoding.marvelcompose.ui

import androidx.compose.material.ScaffoldState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.emlcoding.marvelcompose.navigatePoppingUpToStartDestination
import com.emlcoding.marvelcompose.navigation.NavItem
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun rememberMarvelAppState(
    scaffoldState: ScaffoldState = rememberScaffoldState(),
    navController: NavHostController = rememberNavController(),
    coroutineScope: CoroutineScope = rememberCoroutineScope()
): MarvelAppState = remember(scaffoldState, navController, coroutineScope) { // Si se actualiza cualquiera de los parámetros que lleva el "remmeber", se generará de nuevo el MarvelAppState
    MarvelAppState(scaffoldState, navController, coroutineScope)
}

class MarvelAppState(
    val scaffoldState: ScaffoldState,
    val navController: NavHostController,
    val coroutineScope: CoroutineScope
) {
    companion object {
        val DRAWER_OPTIONS = listOf(NavItem.HOME, NavItem.SETTINGS)
        val BOTTOM_NAV_OPTIONS = listOf(NavItem.CHARACTERS, NavItem.COMICS, NavItem.EVENTS)
    }

    // Sirve para saber qué elemento de la bottom navigation esta seleccionado
    val currentRoute: String @Composable get() = navController.currentBackStackEntryAsState().value?.destination?.route ?: ""

    val showUpNavigation: Boolean @Composable get() = currentRoute !in NavItem.values().map { it.navCommand.route }

    val showBottomNavigation: Boolean @Composable get() =  BOTTOM_NAV_OPTIONS.any { currentRoute.contains(it.navCommand.feature.route)}

    val drawerSelectedIndex: Int @Composable get() = if(showBottomNavigation) {
        DRAWER_OPTIONS.indexOf(NavItem.HOME)
    } else {
        DRAWER_OPTIONS.indexOfFirst { it.navCommand.route == currentRoute }
    }

    fun onUpClick() {
        navController.popBackStack()
    }

    fun onMenuClick() {
        coroutineScope.launch { scaffoldState.drawerState.open() }
    }

    fun onNavItemClick(navItem: NavItem) {
        navController.navigatePoppingUpToStartDestination(navItem.navCommand.route)
    }

    fun onDrawerOptionClick(navItem: NavItem) {
        coroutineScope.launch { scaffoldState.drawerState.close() }
        onNavItemClick(navItem)
    }
}