package com.phoenix.appjumpstart.ui.screens.addItem

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.phoenix.appjumpstart.ui.AppViewModelProvider
import com.phoenix.appjumpstart.ui.state.AddItemViewModel

@Composable
fun AddItemScreen(
    viewModel: AddItemViewModel = viewModel(factory = AppViewModelProvider.Factory),
    onSubmit: () -> Unit
) {
    val uiState by viewModel.addItemUiState.collectAsState()

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
            value = uiState.itemDetails.name,
            onValueChange = {
                viewModel.updateUiState(
                    uiState.itemDetails.copy(name = it)
                )
            },
            placeholder = { Text("Item Name") },
            isError = uiState.isNameError,
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
        if (uiState.isNameError) {
            Text(
                text = "Name cannot be empty",
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodySmall
            )
        }

        // Price Input
        OutlinedTextField(
            value = uiState.itemDetails.price,
            onValueChange = {
                viewModel.updateUiState(
                    uiState.itemDetails.copy(price = it)
                )
            },
            placeholder = { Text("Item Price") },
            isError = uiState.isPriceError,
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
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
        if (uiState.isPriceError) {
            Text(
                text = "Price must be a positive number",
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodySmall
            )
        }

        DropDown(viewModel = viewModel)

        if (uiState.isShippingMethodError) {
            Text(
                text = "Please select a shipping method",
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodySmall
            )
        }

        // Submit Button
        Button(
            onClick = {
                onSubmit()
//                viewModel.validateAndSubmit(navigateBack)
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