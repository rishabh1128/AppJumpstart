package com.phoenix.appjumpstart.data.database

import com.phoenix.appjumpstart.data.model.Item
import com.phoenix.appjumpstart.data.model.ItemDao
import kotlinx.coroutines.flow.Flow

class ItemsRepository(private val itemDao: ItemDao) {
    fun getAllItemsStream(): Flow<List<Item>> = itemDao.getAllItems()

    fun getItemStream(id: Int): Flow<Item?> = itemDao.getItem(id)

    suspend fun getAllItems(): List<Item> = itemDao.getAllItemsSync()

    suspend fun insertItem(item: Item) = itemDao.insert(item)

    suspend fun insertAllItems(items: List<Item>) = itemDao.insertAll(items)

    suspend fun deleteItem(item: Item) = itemDao.delete(item)

    suspend fun updateItem(item: Item) = itemDao.update(item)
}