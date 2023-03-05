package com.project.androidretrofitexample.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.project.androidretrofitexample.MainActivity
import com.project.androidretrofitexample.model.Comment
import com.project.androidretrofitexample.model.Users
import com.project.androidretrofitexample.view.UserInfoActivity
import java.util.*

class CommentViewModel :  ViewModel() {

    private val commentModel = MutableLiveData<List<Comment>>()

    fun getComment(): LiveData<List<Comment>>{
        commentModel.value = UserInfoActivity.data
        return commentModel
    }
}