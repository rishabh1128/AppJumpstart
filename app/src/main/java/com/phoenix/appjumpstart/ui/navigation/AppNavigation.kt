package com.phoenix.appjumpstart.ui.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.phoenix.appjumpstart.ui.screens.addItem.AddItemScreen
import com.phoenix.appjumpstart.ui.screens.grid.GridScreen
import com.phoenix.appjumpstart.ui.screens.list.ListScreen


@Composable
fun AppNavigation(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentScreen = AppRoutes.valueOf(
        backStackEntry?.destination?.route ?: AppRoutes.LIST_VIEW.name
    )
    Scaffold(
        topBar = {
            AppBar(
                currentScreen = currentScreen,
                modifier = modifier
            )
        },
        bottomBar = {
            BottomNavigationBar(
                modifier = modifier,
                navController = navController
            )
        }
    ) { innerPadding ->
        NavHost(
            navController,
            startDestination = AppRoutes.LIST_VIEW.name,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(AppRoutes.LIST_VIEW.name) { ListScreen() }
            composable(AppRoutes.GRID_VIEW.name) { GridScreen() }
            composable(AppRoutes.ADD_ITEM.name) {
                AddItemScreen(navigateBack = {
                    navController.navigate(AppRoutes.GRID_VIEW.name) {
                        popUpTo(navController.graph.startDestinationId) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                })
            }
        }
    }
}