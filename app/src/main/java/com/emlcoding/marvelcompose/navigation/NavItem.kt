package com.emlcoding.marvelcompose.navigation

import androidx.navigation.NavType
import androidx.navigation.navArgument

sealed class NavItem(
    internal val baseRoute: String,
    private val navArgs: List<NavArg> = emptyList()
) {
    object Characters : NavItem("characters")

    object CharacterDetail : NavItem("characterDetail", listOf(NavArg.ItemId)) {
        fun createRoute(itemId: Int) = "$baseRoute/$itemId"
    }

    val route = run {
        // Este run va a devolver la ruta base con sus argumentos tal que: baseRoute/{arg1}/{arg2}/...
        val argKeys = navArgs.map { "{${it.key}}" }
        listOf(baseRoute)
            .plus(argKeys) // Añade todos los elementos a la lista
            .joinToString("/") // Separados por /
    }

    val args = navArgs.map {
        navArgument(it.key) { type = it.navType }
    }
}

enum class NavArg(val key: String, val navType: NavType<*>) {
    ItemId("itemId", NavType.IntType)
}
