package com.example.redditposts.data.retrofit



import com.example.redditposts.data.model.Root
import kotlinx.coroutines.Deferred
import retrofit2.http.GET

interface ApiService {
    @GET("r/aww/hot.json")
    fun getHotRedditPosts(
    ): Deferred<Root>
}