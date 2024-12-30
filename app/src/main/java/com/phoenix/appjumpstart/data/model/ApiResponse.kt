package com.phoenix.appjumpstart.data.model

import kotlinx.serialization.Serializable

@Serializable
data class ApiResponse(
    val status: String,
    val data: Data
)

@Serializable
data class Data(
    val items: List<ApiItem>
)

@Serializable
data class ApiItem(
    val name: String,
    val price: String,
    val extra: String
)

fun ApiItem.toItem(): Item {
    return Item(
        name = this.name,
        price = this.price.toDoubleOrNull() ?: 0.0,
        isSameDayShipping = this.extra == "Same Day Shipping"
    )
}