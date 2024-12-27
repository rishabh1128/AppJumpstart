package com.phoenix.appjumpstart.ui.screens.addItem

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.phoenix.appjumpstart.ui.AppViewModelProvider
import com.phoenix.appjumpstart.ui.state.AddItemViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DropDown(
    viewModel: AddItemViewModel = viewModel(factory = AppViewModelProvider.Factory),
    modifier: Modifier = Modifier
) {

    val uiState by viewModel.addItemUiState.collectAsState()

    val options = listOf(
        "Same Day Shipping",
        "None"
    )

    ExposedDropdownMenuBox(
        expanded = uiState.expanded,
        onExpandedChange = {
            viewModel.setExpanded(it)
        },
        modifier = modifier
    ) {
        OutlinedTextField(
            readOnly = true,
            value = uiState.itemDetails.shippingMethod,
            onValueChange = { },
            placeholder = { Text("Shipping Method") },
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(
                    expanded = uiState.expanded
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
            expanded = uiState.expanded,
            onDismissRequest = {
                viewModel.setExpanded(false)
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
                        viewModel.updateUiState(uiState.itemDetails.copy(shippingMethod = selectionOption))
                        viewModel.setExpanded(false)
                    }
                )
            }
        }
    }
}

@Preview
@Composable
fun DropDownPreview() {
    DropDown()
}