package com.project.androidretrofitexample.Sevice

import com.project.androidretrofitexample.Model.User
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

public interface UserService {

//    @GET("users/{login}")
//    fun getUserbyLogin(@Path("login") login: String): Call<User>
    @GET("users")
    fun getUserbyLogin(): Call<List<User>>
}