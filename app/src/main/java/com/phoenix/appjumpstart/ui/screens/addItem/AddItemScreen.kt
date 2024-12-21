package com.phoenix.appjumpstart.ui.screens.addItem

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddItemScreen() {
    var name by remember { mutableStateOf("") }
    var price by remember { mutableStateOf("") }
    var isNameError by remember { mutableStateOf(false) }
    var isPriceError by remember { mutableStateOf(false) }
    var isShippingMethodError by remember { mutableStateOf(false) }
    val options = listOf(
        "Same Day Shipping",
        "None"
    )
    var expanded by remember { mutableStateOf(false) }
    var shippingMethod by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                horizontal = 32.dp,
                vertical = 48.dp
            ),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        // Name Input
        OutlinedTextField(
            value = name,
            onValueChange = {
                name = it
                isNameError = false
            },
            placeholder = { Text("Item Name") },
            isError = isNameError,
            singleLine = true,
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(40.dp),
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedPlaceholderColor = Color.LightGray,
                focusedPlaceholderColor = Color.LightGray,
                cursorColor = Color.Gray,
                focusedContainerColor = Color(0xfff6f6f6),
                unfocusedContainerColor = Color(0xfff6f6f6),
                focusedBorderColor = Color(0xffe8e8e8),
                unfocusedBorderColor = Color(0xffe8e8e8),
                errorPlaceholderColor = Color.LightGray,
                errorContainerColor = Color(0xfff6f6f6),
            )
        )
        if (isNameError) {
            Text(
                text = "Name cannot be empty",
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodySmall
            )
        }

        // Price Input
        OutlinedTextField(
            value = price,
            onValueChange = {
                price = it
                isPriceError = false
            },
            placeholder = { Text("Item Price") },
            isError = isPriceError,
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(40.dp),
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedPlaceholderColor = Color.LightGray,
                focusedPlaceholderColor = Color.LightGray,
                cursorColor = Color.Gray,
                focusedContainerColor = Color(0xfff6f6f6),
                unfocusedContainerColor = Color(0xfff6f6f6),
                focusedBorderColor = Color(0xffe8e8e8),
                unfocusedBorderColor = Color(0xffe8e8e8),
                errorPlaceholderColor = Color.LightGray,
                errorContainerColor = Color(0xfff6f6f6),
            )
        )
        if (isPriceError) {
            Text(
                text = "Price must be a positive number",
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodySmall
            )
        }

        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = {
                expanded = !expanded
            }
        ) {
            OutlinedTextField(
                readOnly = true,
                value = shippingMethod,
                isError = isShippingMethodError,
                onValueChange = {
                    shippingMethod = it
                    isShippingMethodError = false
                },
                placeholder = { Text("Shipping Method") },
                trailingIcon = {
                    ExposedDropdownMenuDefaults.TrailingIcon(
                        expanded = expanded
                    )
                },
                shape = RoundedCornerShape(40.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    unfocusedPlaceholderColor = Color.LightGray,
                    focusedPlaceholderColor = Color.LightGray,
                    cursorColor = Color.Gray,
                    focusedContainerColor = Color(0xfff6f6f6),
                    unfocusedContainerColor = Color(0xfff6f6f6),
                    focusedBorderColor = Color(0xffe8e8e8),
                    unfocusedBorderColor = Color(0xffe8e8e8),
                    focusedTrailingIconColor = Color.Gray,
                    unfocusedTrailingIconColor = Color.Gray,
                    errorPlaceholderColor = Color.LightGray,
                    errorContainerColor = Color(0xfff6f6f6),
                ),
                modifier = Modifier
                    .menuAnchor()
                    .fillMaxWidth()
            )

            DropdownMenu(
                expanded = expanded,
                onDismissRequest = {
                    expanded = false
                },
                modifier = Modifier
                    .exposedDropdownSize()
                    .background(Color(0xfff6f6f6))
            ) {
                options.forEach { selectionOption ->
                    DropdownMenuItem(
                        text = {
                            Text(
                                text = selectionOption,
                                style = MaterialTheme.typography.bodyMedium,
                                color = Color.LightGray
                            )
                        },
                        onClick = {
                            shippingMethod = selectionOption
                            expanded = false
                        }
                    )

                }
            }
        }

        if (isShippingMethodError) {
            Text(
                text = "Please select a shipping method",
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodySmall
            )
        }

        // Submit Button
        Button(
            onClick = {
                val isNameValid = name.isNotBlank()
                val isPriceValid = price.toDoubleOrNull()
                    ?.let { it > 0 } == true
                val isShippingMethodValid = shippingMethod.isNotBlank()
                if (isNameValid && isPriceValid && isShippingMethodValid) {
                    isNameError = false
                    isPriceError = false
                    isShippingMethodError = false
//                    onItemAdded(Item(name = name, price = price.toDouble()))
//                    onNavigateToGrid()
                } else {
                    isNameError = !isNameValid
                    isPriceError = !isPriceValid
                    isShippingMethodError = !isShippingMethodValid
                }
            },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primary
            )
        ) {
            Text(
                text = "Submit",
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(8.dp)
            )
        }
    }
}