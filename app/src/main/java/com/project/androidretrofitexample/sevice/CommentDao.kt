package com.project.androidretrofitexample.sevice

import androidx.room.*
import com.project.androidretrofitexample.model.Comment

@Dao
interface CommentDao {
    // 모두 출력
    @Query("SELECT * FROM Comment")
    fun getAll(): List<Comment>

    @Insert
    fun insert(todo: Comment)

    @Delete
    fun delete(todo: Comment)

    @Update
    fun update(todo: Comment)
}