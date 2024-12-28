package com.phoenix.appjumpstart.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.RangeSlider
import androidx.compose.material.SliderDefaults
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun FilterDialog(
    onDismiss: () -> Unit,
    onApply: (priceRange: ClosedFloatingPointRange<Float>, isFreeShipping: Boolean) -> Unit,
) {
    var priceRange by remember { mutableStateOf(0f..10000f) }
    var isSameDayShipping by remember { mutableStateOf(false) }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Filter Options") },
        containerColor = Color.White,
        text = {
            Column {
                // Price Range Slider
                Text("Price Range: ₹${priceRange.start.toInt()} - ₹${priceRange.endInclusive.toInt()}")
                RangeSlider(
                    value = priceRange,
                    onValueChange = { priceRange = it },
                    valueRange = 0f..10000f,
                    steps = 10000,
                    colors = SliderDefaults.colors(
                        thumbColor = MaterialTheme.colorScheme.primary,
                        activeTrackColor = Color.LightGray,
                        activeTickColor = MaterialTheme.colorScheme.primary,

                        )
                )

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Checkbox(
                        checked = isSameDayShipping,
                        onCheckedChange = { isSameDayShipping = it }
                    )
                    Text("Same Day Shipping")
                }
            }
        },
        confirmButton = {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Button(onClick = {
                    onApply(
                        priceRange,
                        isSameDayShipping
                    )
                }) {
                    Text("Apply")
                }
            }
        }
    )
}
