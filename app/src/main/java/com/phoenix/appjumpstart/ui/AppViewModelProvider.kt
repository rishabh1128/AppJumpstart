package com.phoenix.appjumpstart.ui

import android.app.Application
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.phoenix.appjumpstart.AppJumpstartApplication
import com.phoenix.appjumpstart.ui.state.AddItemViewModel
import com.phoenix.appjumpstart.ui.state.ItemDisplayViewModel

object AppViewModelProvider {
    val Factory = viewModelFactory {
        // Initializer for ItemDisplayViewModel
        initializer {
            ItemDisplayViewModel(
                jumpstartApplication().container.itemsRepository
            )
        }

        // Initializer for AddItemViewModel
        initializer {
            AddItemViewModel(
                jumpstartApplication().container.itemsRepository
            )
        }
    }
}

/**
 * Extension function to queries for [Application] object and returns an instance of
 * [AppJumpstartApplication].
 */
fun CreationExtras.jumpstartApplication(): AppJumpstartApplication =
    (this[AndroidViewModelFactory.APPLICATION_KEY] as AppJumpstartApplication)