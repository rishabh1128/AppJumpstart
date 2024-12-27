package com.phoenix.appjumpstart.ui.screens.grid

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.phoenix.appjumpstart.data.database.Datasource
import com.phoenix.appjumpstart.data.model.Item
import com.phoenix.appjumpstart.ui.AppViewModelProvider
import com.phoenix.appjumpstart.ui.components.ItemCard
import com.phoenix.appjumpstart.ui.state.ItemDisplayViewModel

@Composable
fun GridScreen(
    itemList: List<Item> = Datasource.items,
    viewModel: ItemDisplayViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        contentPadding = PaddingValues(28.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(
            items = itemList
        ) { item ->
            ItemCard(
                item = item,
                isList = false
            )
        }
    }
}