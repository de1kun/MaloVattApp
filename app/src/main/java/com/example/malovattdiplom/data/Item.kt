package com.example.malovattdiplom.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName="item_table")
data class Item (
    @PrimaryKey(autoGenerate = true)
    val id:Int,
    val item_name: String,
    val vatt_count:Int,
    val price:Int
)
