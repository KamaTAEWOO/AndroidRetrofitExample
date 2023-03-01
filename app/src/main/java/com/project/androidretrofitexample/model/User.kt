package com.project.androidretrofitexample.model

import com.google.gson.annotations.SerializedName

public data class User(
    @SerializedName("login") val login:String,
    @SerializedName("avatar_url") val avaterUrl:String,
    @SerializedName("name") val name:String,
    @SerializedName("company") val company:String,
    @SerializedName("location") val location:String,
    @SerializedName("followers") val followers:Int,
    @SerializedName("following") val following:Int,
    @SerializedName("created_at") val createdAt:String,
    @SerializedName("updated_at") val updatedAt:String
)
