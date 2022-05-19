package com.example.redditposts.domain

import com.example.redditposts.data.model.Root
import com.example.redditposts.data.retrofit.RetrofitImpl

class RemoteRepositoryImpl(private val api: RetrofitImpl): IRemoteRepository<Root> {
    override suspend fun getHotRedditPosts(): Root {
        return api.getHotRedditPosts()
    }
}