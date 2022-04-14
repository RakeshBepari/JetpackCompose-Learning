package com.example.jetpackcomposelearning.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Todo")
data class Todo(
    val title:String,
    val description:String
){
    @PrimaryKey
    var id : Int? = null
}
