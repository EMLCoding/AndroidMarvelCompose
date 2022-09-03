package com.emlcoding.marvelcompose

import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController

fun NavHostController.navigatePoppingUpToStartDestination(route: String) {
    navigate(route) {
        popUpTo(graph.findStartDestination().id) {
            saveState = true
        }

        // Esto es para evitar que se cargue varias veces la misma pagina si se pulsa varias veces el mismo icono de la bottom navigation bar
        launchSingleTop = true

        // Esto es para que la navegacion siempre termine volviendo a la pagina principal de la App
        restoreState = true
    }
}