package com.phoenix.appjumpstart.data.database

import com.phoenix.appjumpstart.data.model.Item
import com.phoenix.appjumpstart.data.model.ItemDao
import kotlinx.coroutines.flow.Flow

class ItemsRepository(private val itemDao: ItemDao) {
    fun getAllItemsStream(): Flow<List<Item>> = itemDao.getAllItems()

    fun getSearchedItemsStream(searchQuery: String): Flow<List<Item>> = itemDao.getSearchedItems(searchQuery)

    suspend fun getItemCount(): Int = itemDao.getItemCount()

    suspend fun insertItem(item: Item) = itemDao.insert(item)

    suspend fun insertAllItems(items: List<Item>) = itemDao.insertAll(items)

    suspend fun deleteItem(item: Item) = itemDao.delete(item)

    suspend fun updateItem(item: Item) = itemDao.update(item)
}