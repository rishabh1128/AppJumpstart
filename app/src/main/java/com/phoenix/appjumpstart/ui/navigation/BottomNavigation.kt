package com.phoenix.appjumpstart.ui.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState

@Composable
fun BottomNavigationBar(
    modifier: Modifier = Modifier,
    navController: NavController
) {
    Column {
        HorizontalDivider(
            color = Color(0xFFDDDDDD),
            thickness = 0.5.dp
        )
        NavigationBar(
            modifier = modifier,
            containerColor = Color(0xFFFAFAFA)
        ) {
            val currentDestination by navController.currentBackStackEntryAsState()
            val currentRoute = currentDestination?.destination?.route

            AppRoutes.entries.forEach { screen ->
                val isSelected = currentRoute == screen.name
                NavigationBarItem(
                    icon = {
                        Box(
                            modifier = Modifier
                                .size(36.dp)
                                .clip(CircleShape)
                                .background(
                                    if (isSelected) MaterialTheme.colorScheme.primary
                                    else Color(0xffe8e8e8)
                                )
//                            .padding(4.dp)
                        )
                    },
                    label = {},
                    selected = false,
                    onClick = {
                        if(currentRoute!=screen.name){
                            navController.navigate(screen.name) {
                                popUpTo(navController.graph.startDestinationId) { saveState = true }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    },
//                selectedContentColor = MaterialTheme.colorScheme.primary,
//                unselectedContentColor = Color(0xffe8e8e8)
                )
            }
        }
    }
}
