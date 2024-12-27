package com.phoenix.appjumpstart.ui.state

import com.phoenix.appjumpstart.data.model.Item

data class AddItemUiState(
    val itemDetails : ItemDetails = ItemDetails(),
    val isNameError: Boolean = false,
    val isPriceError: Boolean = false,
    val isShippingMethodError: Boolean = false,
    val expanded: Boolean = false
)
data class ItemDetails(
    val id: Int = 0,
    val name: String = "",
    val price: String = "",
    val shippingMethod: String = ""
)
fun ItemDetails.toItem(): Item = Item(
    id = id,
    name = name,
    price = price.toDoubleOrNull() ?: 0.0,
    isSameDayShipping = shippingMethod == "Same Day Shipping"
)

fun Item.toItemDetails(): ItemDetails = ItemDetails(
    id = id,
    name = name,
    price = price.toString(),
    shippingMethod = if (isSameDayShipping) "Same Day Shipping" else "None"
)