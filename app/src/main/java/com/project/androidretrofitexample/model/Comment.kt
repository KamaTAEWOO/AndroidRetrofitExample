package com.project.androidretrofitexample.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Comment(
    val title: String
){
    @PrimaryKey(autoGenerate = true) var id: Int = 0
}
