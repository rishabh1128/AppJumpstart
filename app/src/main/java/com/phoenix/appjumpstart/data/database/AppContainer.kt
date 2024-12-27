package com.phoenix.appjumpstart.data.database

import android.content.Context

/**
 * App container for Dependency injection.
 */

class AppContainer(private val context: Context) {
    /**
     * Implementation for [ItemsRepository]
     */
    val itemsRepository: ItemsRepository by lazy {
        ItemsRepository(AppDatabase.getDatabase(context).itemDao())
    }
}