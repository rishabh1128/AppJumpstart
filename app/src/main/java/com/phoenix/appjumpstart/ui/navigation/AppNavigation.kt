package com.phoenix.appjumpstart.ui.navigation

import AppBar
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.phoenix.appjumpstart.ui.AppViewModelProvider
import com.phoenix.appjumpstart.ui.screens.addItem.AddItemScreen
import com.phoenix.appjumpstart.ui.screens.grid.GridScreen
import com.phoenix.appjumpstart.ui.screens.list.ListScreen
import com.phoenix.appjumpstart.ui.state.AddItemViewModel
import com.phoenix.appjumpstart.ui.state.ItemDisplayViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppNavigation(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {
    val itemViewModel: ItemDisplayViewModel = viewModel(factory = AppViewModelProvider.Factory)
    val addViewModel: AddItemViewModel = viewModel(factory = AppViewModelProvider.Factory)
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentScreen = AppRoutes.valueOf(
        backStackEntry?.destination?.route ?: AppRoutes.LIST_VIEW.name
    )
    val navigateBack = {
        navController.navigate(AppRoutes.GRID_VIEW.name) {
            popUpTo(navController.graph.startDestinationId) {
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }
    }

    val onSubmit = {
        addViewModel.validateAndSubmit(navigateBack)
    }
    Scaffold(
        modifier = modifier
            .background(Color(0xFFe6eaf6))
            .windowInsetsPadding(WindowInsets.systemBars),
        topBar = {
            AppBar(
                modifier = Modifier.statusBarsPadding(),
                currentScreen = currentScreen,
                viewModel = itemViewModel,
                onAddClicked = onSubmit
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
            composable(AppRoutes.LIST_VIEW.name) {
                ListScreen(itemViewModel)
            }
            composable(AppRoutes.GRID_VIEW.name) { GridScreen(itemViewModel) }
            composable(AppRoutes.ADD_ITEM.name) {
                AddItemScreen(
                    viewModel = addViewModel,
                    onSubmit = onSubmit
                )
            }
        }
    }
}