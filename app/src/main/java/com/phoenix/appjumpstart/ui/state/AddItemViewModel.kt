package com.phoenix.appjumpstart.ui.state

import androidx.lifecycle.ViewModel
import com.phoenix.appjumpstart.data.database.ItemsRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class AddItemViewModel(
    private val itemsRepository: ItemsRepository
) : ViewModel() {
    private val _addItemUiState = MutableStateFlow(AddItemUiState())
    val addItemUiState: StateFlow<AddItemUiState> = _addItemUiState.asStateFlow()

    fun updateUiState(itemDetails: ItemDetails) {
        _addItemUiState.update {
            it.copy(
                itemDetails = itemDetails,
                isNameError = false,
                isPriceError = false,
                isShippingMethodError = false
            )
        }
    }

    private fun validateInput(): Boolean {
        _addItemUiState.update { itemUiState ->
            itemUiState.copy(
                isNameError = itemUiState.itemDetails.name.isBlank(),
                isPriceError = itemUiState.itemDetails.price.toDoubleOrNull()
                    ?.let { it > 0 } == false || itemUiState.itemDetails.price.isBlank(),
                isShippingMethodError = itemUiState.itemDetails.shippingMethod.isBlank()
            )
        }
        return with(_addItemUiState.value) {
            !isNameError && !isPriceError && !isShippingMethodError
        }
    }

    private fun resetState(){
        _addItemUiState.value = AddItemUiState()
    }

    suspend fun validateAndSubmit() : Boolean {
        val isValid = validateInput()
        if (isValid) {
            // Submit the item
            itemsRepository.insertItem(_addItemUiState.value.itemDetails.toItem())
            resetState()
        }
        return isValid
    }

    fun setExpanded(expanded: Boolean) {
        _addItemUiState.update { it.copy(expanded = expanded) }
    }
}