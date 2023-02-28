package com.project.androidretrofitexample.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.project.androidretrofitexample.MainActivity
import com.project.androidretrofitexample.model.Users

class UserViewModel :  ViewModel() {

    private val usersModel = MutableLiveData<List<Users>>()

    init {
        // 230227 tw 받아온 데이터 담기
        usersModel.value = MainActivity.users
    }

    fun getUsers(): LiveData<List<Users>>{
        return usersModel
    }
}