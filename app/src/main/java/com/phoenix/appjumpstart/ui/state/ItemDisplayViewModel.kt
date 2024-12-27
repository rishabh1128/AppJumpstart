package com.phoenix.appjumpstart.ui.state

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.phoenix.appjumpstart.data.database.Datasource
import com.phoenix.appjumpstart.data.database.ItemsRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class ItemDisplayViewModel(private val itemsRepository: ItemsRepository) : ViewModel(){
    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }
    init {
        checkAndInsertInitialData()
    }

    private fun checkAndInsertInitialData() {
        viewModelScope.launch {
            val items = itemsRepository.getAllItems()
            if (items.isEmpty()) {
                //insert temp data if empty, replace with API data later
                itemsRepository.insertAllItems(Datasource.items)
            }
        }
    }
    val itemDisplayUiState: StateFlow<ItemDisplayUiState> =
        itemsRepository.getAllItemsStream().map{
            ItemDisplayUiState(it)
        }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
                initialValue = ItemDisplayUiState()
            )
}