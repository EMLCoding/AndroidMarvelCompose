package com.emlcoding.marvelcompose.ui.navigation

import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.material.Icon
import androidx.compose.material.Text
import com.emlcoding.marvelcompose.navigation.NavItem

@Composable
fun AppBottomNavigation(currentRoute: String, onNavItemClick: (NavItem) -> Unit) {
    BottomNavigation {
        NavItem.values().forEach { item ->
            val title = stringResource(item.title)
            BottomNavigationItem(
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