package com.phoenix.appjumpstart.ui.state

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.phoenix.appjumpstart.data.database.Datasource
import com.phoenix.appjumpstart.data.database.ItemsRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ItemDisplayViewModel(
    private val itemsRepository: ItemsRepository
) : ViewModel() {
    companion object {
        private var isInitialized = false
        private const val TIMEOUT_MILLIS = 5_000L
    }

    private var searchJob: Job? = null
    private val _itemDisplayUiState = MutableStateFlow(ItemDisplayUiState())
    val itemDisplayUiState: StateFlow<ItemDisplayUiState>
        get() = _itemDisplayUiState.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
            initialValue = ItemDisplayUiState()
        )

    // Use StateFlow for searchQuery to make it observable
    private val _searchQuery = MutableStateFlow("")
    private val searchQuery: StateFlow<String> = _searchQuery.asStateFlow()

    init {
        Log.d(
            "ItemDisplayViewModel",
            "Init called"
        )
        viewModelScope.launch {
            if (!isInitialized) {
                isInitialized = true
                checkAndInsertInitialData()
            }
            observe()
        }
    }

    private suspend fun checkAndInsertInitialData() {
        val count = itemsRepository.getItemCount()
        if (count == 0) {
            //insert temp data if empty, replace with API data later
            itemsRepository.insertAllItems(Datasource.items)
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    private suspend fun observe() {
        searchQuery.flatMapLatest { query ->
            Log.d(
                "ItemDisplayViewModel",
                "Search query updated: $query"
            )
            itemsRepository.getSearchedItemsStream(query)
        }
            .collect { items ->
                Log.d(
                    "ItemDisplayViewModel",
                    "Items collected: ${items.size}"
                )
                _itemDisplayUiState.value =
                    _itemDisplayUiState.value.copy(items = items)

                Log.d(
                    "ItemDisplayViewModel",
                    "UI State updated: ${itemDisplayUiState.value}"
                )
            }
    }

    fun updateSearchQuery(query: String) {
        _itemDisplayUiState.value = _itemDisplayUiState.value.copy(searchValue = query)
        searchJob?.cancel()
        searchJob = launchSearchJob()
    }

    fun onSearchSubmit() {
        searchJob?.cancel()
        search()
    }

    private fun search() {
        _searchQuery.update {
            _itemDisplayUiState.value.searchValue
        }
    }

    private fun launchSearchJob(): Job {
        return viewModelScope.launch {
            delay(2000L)
            search()
        }
    }
}
