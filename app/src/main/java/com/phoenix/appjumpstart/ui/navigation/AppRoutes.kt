package com.phoenix.appjumpstart.ui.navigation

import androidx.annotation.StringRes
import com.phoenix.appjumpstart.R

enum class AppRoutes(
    @StringRes val title: Int,
    @StringRes val buttonText: Int,
    val hasSearch: Boolean,
) {
    LIST_VIEW(
        title = R.string.explore,
        buttonText = R.string.filter,
        hasSearch = true
    ),
    GRID_VIEW(
        title = R.string.explore,
        buttonText = R.string.filter,
        hasSearch = true
    ),
    ADD_ITEM(
        title = R.string.add_item,
        buttonText = R.string.add,
        hasSearch = false
    )
}