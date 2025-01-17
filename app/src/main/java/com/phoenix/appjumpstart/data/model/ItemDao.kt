package com.phoenix.appjumpstart.data.model

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface ItemDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(item: Item)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(items: List<Item>)

    @Update
    suspend fun update(item: Item)

    @Delete
    suspend fun delete(item: Item)

    @Query("SELECT * from items WHERE name LIKE '%' || :searchQuery || '%'")
    fun getSearchedItems(searchQuery: String): Flow<List<Item>>

    @Query("SELECT * from items")
    fun getAllItems(): Flow<List<Item>>

    @Query("SELECT COUNT(*) FROM items")
    suspend fun getItemCount(): Int
}