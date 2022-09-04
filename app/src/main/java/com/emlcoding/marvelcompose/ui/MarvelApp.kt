package com.emlcoding.marvelcompose.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.emlcoding.marvelcompose.R
import com.emlcoding.marvelcompose.navigatePoppingUpToStartDestination
import com.emlcoding.marvelcompose.navigation.Navigation
import com.emlcoding.marvelcompose.navigation.AppBottomNavigation
import com.emlcoding.marvelcompose.navigation.DrawerContent
import com.emlcoding.marvelcompose.navigation.NavItem
import com.emlcoding.marvelcompose.ui.common.AppBarIcon
import com.emlcoding.marvelcompose.ui.theme.MarvelComposeTheme
import kotlinx.coroutines.launch

@Composable
fun MarvelApp() {
    val navController = rememberNavController()
    // Sirve para saber qué elemento de la bottom navigation esta seleccionado
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route ?: ""
    val showUpNavigation = currentRoute !in NavItem.values().map { it.navCommand.route }
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()
    val drawerOptions = listOf(NavItem.HOME, NavItem.SETTINGS)
    val bottomNavOptions = listOf(NavItem.CHARACTERS, NavItem.COMICS, NavItem.EVENTS)

    val showBottomNavigation = bottomNavOptions.any { currentRoute.contains(it.navCommand.feature.route)}
    val drawerSelectedIndex = if(showBottomNavigation) {
        drawerOptions.indexOf(NavItem.HOME)
    } else {
        drawerOptions.indexOfFirst { it.navCommand.route == currentRoute }
    }

    MarvelScreen {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text(stringResource(id = R.string.app_name)) },
                    navigationIcon = {
                        if (showUpNavigation) {
                            AppBarIcon(
                                imageVector = Icons.Default.ArrowBack,
                                onClick = { navController.popBackStack() }
                            )
                        } else {
                            AppBarIcon(
                                imageVector = Icons.Default.Menu,
                                onClick = { scope.launch { scaffoldState.drawerState.open() } }
                            )
                        }
                    }
                )
            },
            bottomBar = {
                if(showBottomNavigation) {
                    AppBottomNavigation(bottomNavOptions = bottomNavOptions, currentRoute = currentRoute) {
                        navController.navigatePoppingUpToStartDestination(it.navCommand.route)
                    }
                }
            },
            drawerContent = {
                DrawerContent(drawerOptions = drawerOptions, selectedIndex = drawerSelectedIndex, onOptionClick = {navItem ->
                    scope.launch { scaffoldState.drawerState.close() }
                    navController.navigate(navItem.navCommand.route)
                })
            },
            scaffoldState = scaffoldState
        ) { padding ->
            Box(modifier = Modifier.padding(padding)) {
                Navigation(navController)
            }
        }
    }
}

@Composable
fun MarvelScreen(content: @Composable () -> Unit) {
    MarvelComposeTheme {
        // A surface container using the 'background' color from the theme
        Surface(color = MaterialTheme.colors.background) {
            content()
        }
    }
}