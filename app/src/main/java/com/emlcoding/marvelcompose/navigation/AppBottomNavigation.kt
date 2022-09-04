package com.emlcoding.marvelcompose.navigation

import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.material.Icon
import androidx.compose.material.Text

@Composable
fun AppBottomNavigation(
    bottomNavOptions: List<NavItem>,
    currentRoute: String,
    onNavItemClick: (NavItem) -> Unit
) {
    BottomNavigation {
        bottomNavOptions.forEach { item ->
            val title = stringResource(item.title)
            BottomNavigationItem(
                // Si estamos tanto en la pagina principal como en la de detalle de una seccion, se colorea el icono de dicha seccion en la bottom navigation bar
                selected = currentRoute.contains(item.navCommand.feature.route),
                icon = {
                    Icon(
                        imageVector = item.icon,
                        contentDescription = title
                    )
                },
                label = { Text(text = title) },
                onClick = { onNavItemClick(item) }
            )
        }
    }
}