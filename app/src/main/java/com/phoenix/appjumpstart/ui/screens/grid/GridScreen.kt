package com.phoenix.appjumpstart.ui.screens.grid

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.phoenix.appjumpstart.data.database.Datasource
import com.phoenix.appjumpstart.ui.components.ItemCard

@Composable
fun GridScreen() {
    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        contentPadding = PaddingValues(28.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ){
        items(Datasource.items.size) {
             ItemCard(item = Datasource.items[it], isList = false)
        }
    }
}