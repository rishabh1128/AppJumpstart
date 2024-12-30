package com.phoenix.appjumpstart.ui.screens.list

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.phoenix.appjumpstart.ui.AppViewModelProvider
import com.phoenix.appjumpstart.ui.components.ErrorDialog
import com.phoenix.appjumpstart.ui.components.ItemCard
import com.phoenix.appjumpstart.ui.state.ItemDisplayViewModel

@Composable
fun ListScreen(
    viewModel: ItemDisplayViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    val uiState by viewModel.itemDisplayUiState.collectAsState()
    val errorMessage = viewModel.errorMessage
//    Log.d(
//        "ListScreen",
//        "UI State updated: ${uiState.items.size} items"
//    )
    if (errorMessage != null) {
        ErrorDialog(message = errorMessage) {
            viewModel.clearErrorMessage()
        }
    }
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(28.dp), // Remove content padding
        verticalArrangement = Arrangement.Top // Stack items without extra space
    ) {
        items(
            items = uiState.items,
            key = { item -> item.id }
        ) { item ->
            ItemCard(
                item = item,
                isList = true
            )
        }
    }
}