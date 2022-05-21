package com.example.redditposts.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.redditposts.domain.model.Children
import com.example.redditposts.domain.model.Root
import com.example.redditposts.data.retrofit.ApiService
import com.example.redditposts.domain.IRemoteRepository
import kotlinx.coroutines.flow.Flow

class RemoteRepositoryImpl(private val api: ApiService): IRemoteRepository {
    override suspend fun getHotRedditPosts(): Root {
        return api.getHotRedditPosts()
    }

    override suspend fun getHotRedditPagingPosts(): Flow<PagingData<Children>> {
        return Pager(
            config = PagingConfig(
                pageSize = 10,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                PostPagingSource(api)
            }
        ).flow
    }
}