package com.example.redditposts.domain

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.redditposts.data.model.Children
import com.example.redditposts.data.retrofit.ApiService
import com.example.redditposts.data.retrofit.RetrofitImpl
import retrofit2.HttpException
import java.io.IOException

class PostPagingSource(val api: ApiService) : PagingSource<String, Children>() {
    override fun getRefreshKey(state: PagingState<String, Children>): String? {
        return state.anchorPosition?.let { state.closestPageToPosition(it)?.nextKey }
    }

    override suspend fun load(params: LoadParams<String>): LoadResult<String, Children> {
        return try {
            val pageNumber = params.key ?: ""
            val response = api.getHotRedditPosts(pageNumber)
            val nextKey = if (response.data.children.isNotEmpty()) {
                response.data.after
            } else {
                ""
            }
            LoadResult.Page(
                data = response.data.children,
                prevKey = "",
                nextKey = nextKey
            )
        } catch (e: IOException) {
            LoadResult.Error(e)
        } catch (e: HttpException) {
            LoadResult.Error(e)
        }
    }
}