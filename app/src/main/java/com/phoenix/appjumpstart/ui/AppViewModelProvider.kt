package com.phoenix.appjumpstart.ui

import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.phoenix.appjumpstart.ui.state.AddItemViewModel
import com.phoenix.appjumpstart.ui.state.ItemDisplayViewModel

object AppViewModelProvider {
    val Factory = viewModelFactory {
        // Initializer for ItemDisplayViewModel
        initializer {
            ItemDisplayViewModel()
        }

        // Initializer for AddItemViewModel
        initializer {
            AddItemViewModel()
        }
    }
}