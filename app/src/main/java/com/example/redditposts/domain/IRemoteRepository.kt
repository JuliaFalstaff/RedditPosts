package com.example.redditposts.domain

import androidx.paging.PagingData
import com.example.redditposts.domain.model.Children
import com.example.redditposts.domain.model.Root
import kotlinx.coroutines.flow.Flow

interface IRemoteRepository {
    suspend fun getHotRedditPosts(): Root
    suspend fun getHotRedditPagingPosts(): Flow<PagingData<Children>>
}