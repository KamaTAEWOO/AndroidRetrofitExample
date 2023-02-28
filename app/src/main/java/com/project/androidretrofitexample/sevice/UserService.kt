package com.project.androidretrofitexample.sevice

import com.project.androidretrofitexample.model.User
import com.project.androidretrofitexample.model.Users
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

public interface UserService {

    @GET("users")
    fun getUsers(): Call<List<Users>>

    @GET("users/{name}")
    fun getUserByLogin(
        @Path("name") name: String
    ): Call<User>
}