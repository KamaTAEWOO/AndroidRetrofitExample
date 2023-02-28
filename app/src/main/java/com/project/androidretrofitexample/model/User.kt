package com.project.androidretrofitexample.model

import com.google.gson.annotations.SerializedName

public data class User(
//    public var login:String,
//    public var avaterUrl:String,
//    public var name:String,
//    public var company:String,
//    public var location:String,
//    public var followers:Int,
//    public var following:Int,
//    public var createdAt:String,
//    public var updatedAt:String

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
