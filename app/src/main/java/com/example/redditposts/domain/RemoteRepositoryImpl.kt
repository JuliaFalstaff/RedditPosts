package com.example.redditposts.domain

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.redditposts.data.model.Children
import com.example.redditposts.data.model.Root
import com.example.redditposts.data.retrofit.ApiService
import com.example.redditposts.data.retrofit.RetrofitImpl
import kotlinx.coroutines.flow.Flow

class RemoteRepositoryImpl(private val api: ApiService): IRemoteRepository {
//    override suspend fun getHotRedditPosts(page: String): Root {
//        return api.getHotRedditPosts(page)
//    }

    override suspend fun getHotRedditPosts(): Flow<PagingData<Children>> {
        return Pager(
            config = PagingConfig(
                pageSize = 10,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                PostPagingSource(api = api)
            }
        ).flow
    }
}