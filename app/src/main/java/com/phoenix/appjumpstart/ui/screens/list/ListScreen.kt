package com.phoenix.appjumpstart.ui.screens.list

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.phoenix.appjumpstart.data.database.Datasource
import com.phoenix.appjumpstart.data.model.Item
import com.phoenix.appjumpstart.ui.AppViewModelProvider
import com.phoenix.appjumpstart.ui.components.ItemCard
import com.phoenix.appjumpstart.ui.state.ItemDisplayViewModel

@Composable
fun ListScreen(
    itemList: List<Item> = Datasource.items,
    viewModel : ItemDisplayViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(28.dp), // Remove content padding
        verticalArrangement = Arrangement.Top // Stack items without extra space
    ) {
        items(
            items = itemList,
            key = { item -> item.id }
        ) { item ->
            ItemCard(
                item = item,
                isList = true
            )
        }
    }
}