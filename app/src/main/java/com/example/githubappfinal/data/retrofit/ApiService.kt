package com.example.githubappfinal.data.retrofit

import com.example.githubappfinal.data.response.DetailUserResponse
import com.example.githubappfinal.data.response.User
import com.example.githubappfinal.data.response.UserResponse
import retrofit2.Call
import retrofit2.http.*

interface ApiService {

    @GET("search/users")
    @Headers("Authorization: <Personal Token>")
    fun getUsers(
        @Query("q") q: String
    ): Call<UserResponse>

    @GET("users/{username}")
    @Headers("Authorization: <Personal Token>")
    fun getUserDetail(
        @Path("username") username: String
    ): Call<DetailUserResponse>

    @GET("users/{username}/followers")
    @Headers("Authorization: <Personal Token>")
    fun getFollowers(
        @Path("username") username: String
    ): Call<ArrayList<User>>

    @GET("users/{username}/following")
    @Headers("Authorization: <Personal Token>")
    fun getFollowing(
        @Path("username") username: String
    ): Call<ArrayList<User>>

}