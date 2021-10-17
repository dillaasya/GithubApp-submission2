package com.adillasyafira.githubapp.api

import com.adillasyafira.githubapp.model.*
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("search/users")
    @Headers("Authorization: token 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")
    fun getSearchUsers(
        @Query("q") search: String
    ): Call<SearchUserResponse>

    @GET("users")
    @Headers("Authorization: token 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")
    fun getUsers(): Call<ArrayList<User>>

    @GET("users/{login}")
    @Headers("Authorization: token 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")
    fun getDetailUsers(
        @Path("login") login: String
    ): Call<DetailUserResponse>

    @GET("users/{login}/followers")
    @Headers("Authorization: token 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")
    fun getDetailUsersFollowers(
        @Path("login") login: String
    ): Call<List<FollowResponseItem>>

    @GET("users/{login}/following")
    @Headers("Authorization: token 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")
    fun getDetailUsersFollowing(
        @Path("login") login: String
    ): Call<List<FollowResponseItem>>

}