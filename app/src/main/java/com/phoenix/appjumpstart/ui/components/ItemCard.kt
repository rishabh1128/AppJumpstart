package com.phoenix.appjumpstart.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.phoenix.appjumpstart.data.database.Datasource
import com.phoenix.appjumpstart.data.model.Item
import com.phoenix.appjumpstart.ui.theme.AppJumpstartTheme

@Composable
fun ItemCard(
    item: Item,
    modifier: Modifier = Modifier,
    isList: Boolean = true
) {
    if (!isList) {
        Card(
            modifier = modifier
                .padding(8.dp)
        ) {
            Column(modifier) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(1f)
                        .background(Color.LightGray)
                )
                Text(
                    text = item.name,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                )
                Text(text = "₹${item.price}")
            }
        }
    } else {
        Row(
            modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(58.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .background(Color(0xffe8e8e8))
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column(
                modifier = Modifier
                    .padding(4.dp)
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    text = item.name,
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                )
                Row(modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = "MRP: ",
                        color = Color.Gray
                    )
                    Text(text = "₹${item.price}")
                    if (item.isSameDayShipping) {
                        Spacer(modifier = Modifier.weight(1f))
                        Text(
                            text = "Same Day Shipping",
                            style = MaterialTheme.typography.bodySmall,
                            color = Color.Gray
                        )
                    }
                }
                HorizontalDivider(
                    color = Color(0xFFDDDDDD),
                    thickness = 1.dp
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ItemCardPreview() {
    AppJumpstartTheme {
        ItemCard(
            item = Datasource.items[0],
            isList = true
        )
    }
}