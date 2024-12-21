package com.phoenix.appjumpstart.ui.screens.list

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.phoenix.appjumpstart.data.database.Datasource
import com.phoenix.appjumpstart.ui.components.ItemCard

@Composable
fun ListScreen() {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(28.dp), // Remove content padding
        verticalArrangement = Arrangement.Top // Stack items without extra space
    ) {
        items(Datasource.items.size) { index ->
            ItemCard(
                item = Datasource.items[index],
                isList = true
            )
        }
    }
}