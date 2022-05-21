package com.example.redditposts.data.retrofit


import com.example.redditposts.domain.model.Root
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("r/aww/hot.json")
    suspend fun getHotRedditPagingPosts(
            @Query("after") after: String,
            @Query("before") before: String
    ): Root

    @GET("r/aww/hot.json")
    suspend fun getHotRedditPosts(): Root
}