package com.phoenix.appjumpstart.ui.state

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.phoenix.appjumpstart.data.database.Datasource
import com.phoenix.appjumpstart.data.database.ItemsRepository
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
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
    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery.asStateFlow()

    private val _priceRange = MutableStateFlow<ClosedFloatingPointRange<Float>>(0f..10000f)
    val priceRange: StateFlow<ClosedFloatingPointRange<Float>> = _priceRange.asStateFlow()

    private val _shippingFilter = MutableStateFlow(false)
    val shippingFilter: StateFlow<Boolean> = _shippingFilter.asStateFlow()

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
            observeFiltersAndSearch()
        }
    }

    private suspend fun checkAndInsertInitialData() {
        val count = itemsRepository.getItemCount()
        if (count == 0) {
            //insert temp data if empty, replace with API data later
            itemsRepository.insertAllItems(Datasource.items)
        }
    }

    // Observe changes in search query, price range, and shipping filter
    private fun observeFiltersAndSearch() {
        combine(
            searchQuery,
            priceRange,
            shippingFilter,
            itemsRepository.getAllItemsStream() // Observe database changes
        ) { query, range, shipping, items ->
            // Apply filters whenever any of the StateFlows or database changes
            items.filter { item ->
                item.name.contains(
                    query,
                    ignoreCase = true
                ) &&
                        (range.contains(item.price)) &&
                        (!shipping || item.isSameDayShipping)
            }
        }.onEach { filteredItems ->
            // Update the items StateFlow with the filtered list
            _itemDisplayUiState.update {
                it.copy(items = filteredItems)
            }
        }
            .launchIn(viewModelScope)
    }


    fun updateSearchQuery(query: String) {
        _itemDisplayUiState.update { it.copy(searchValue = query) }
        searchJob?.cancel()
        searchJob = launchSearchJob()
    }

    fun updatePriceFilter(priceRange: ClosedFloatingPointRange<Float>) {
        _priceRange.value = priceRange
    }

    fun updateShippingFilter(isSameDayShipping: Boolean) {
        _shippingFilter.value = isSameDayShipping
    }

    fun onSearchSubmit() {
        searchJob?.cancel()
        search()
    }

    private fun search() {
        _searchQuery.update{
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
