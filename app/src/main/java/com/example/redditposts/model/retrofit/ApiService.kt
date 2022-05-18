package com.example.redditposts.model.retrofit



import com.example.redditposts.model.data.Root
import kotlinx.coroutines.Deferred
import retrofit2.http.GET

interface ApiService {
    @GET("r/aww/hot.json")
    fun getHotRedditPosts(
    ): Deferred<List<Root>>
}