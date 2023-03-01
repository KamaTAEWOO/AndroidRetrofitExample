package com.project.androidretrofitexample.sevice

import com.project.androidretrofitexample.model.User
import com.project.androidretrofitexample.model.Users
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

public interface UserService {

    //  230301 tw Users Data
    @GET("users")
    fun getUsers(): Call<List<Users>>

    //  230301 tw User Data
    @GET("users/{name}")
    fun getUserByLogin(
        @Path("name") name: String
    ): Call<User>
}