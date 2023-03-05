package com.project.androidretrofitexample.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.project.androidretrofitexample.model.Comment
import com.project.androidretrofitexample.sevice.CommentDao


@Database(entities = [Comment::class], version = 1)
abstract class CommentDatabase : RoomDatabase() {
    abstract fun commentDao(): CommentDao
}