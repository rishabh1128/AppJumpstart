package com.phoenix.appjumpstart.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.phoenix.appjumpstart.data.model.Item

@Composable
fun ItemCard(
    item: Item,
    modifier: Modifier = Modifier,
    isList: Boolean = true
) {
    Card(
        modifier = Modifier
            .padding(8.dp)
    ) {
        if (isList) {
            Column {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(1f)
                        .background(Color.LightGray)
                )
                Text(
                    text = item.name,
                    style = MaterialTheme.typography.titleMedium
                )
                Text(text = "₹${item.price}")
            }
        }
        else{

        }
    }
}