package com.phoenix.appjumpstart.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "items")
data class Item(
    @PrimaryKey(autoGenerate = true)
    val id:Int = 0,
    val name:String = "",
    val price:Double = 0.0,
    val isSameDayShipping: Boolean = false
)
