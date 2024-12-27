package com.phoenix.appjumpstart.ui.state

import com.phoenix.appjumpstart.data.model.Item

data class ItemDisplayUiState(
    val items: List<Item> = emptyList(),
    val searchQuery: String = "",
    val filterByPriceRange: IntRange? = null,
    val filterByShipping: Boolean = false
)