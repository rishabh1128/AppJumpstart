package com.phoenix.appjumpstart.data.model

data class Item(
    val id:Int = 0,
    val name:String = "",
    val price:Double = 0.0,
    val isSameDayShipping: Boolean = false
)
