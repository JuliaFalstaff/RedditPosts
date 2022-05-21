package com.example.redditposts.domain

import androidx.paging.PagingData
import com.example.redditposts.data.model.Children
import com.example.redditposts.data.model.Root
import kotlinx.coroutines.flow.Flow

interface IRemoteRepository {
//    suspend fun getHotRedditPosts(page: String): Root
    suspend fun getHotRedditPosts(): Flow<PagingData<Children>>
}