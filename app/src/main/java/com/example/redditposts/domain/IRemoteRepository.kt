package com.example.redditposts.domain

interface IRemoteRepository<T> {
    suspend fun getHotRedditPosts(): T
}