package com.example.redditposts.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.redditposts.domain.model.Children
import com.example.redditposts.data.retrofit.ApiService
import retrofit2.HttpException
import java.io.IOException

class PostPagingSource(val apiService: ApiService) : PagingSource<String, Children>() {
    override fun getRefreshKey(state: PagingState<String, Children>): String? {
        return state.anchorPosition?.let { state.closestPageToPosition(it)?.nextKey }
    }

    override suspend fun load(params: LoadParams<String>): LoadResult<String, Children> {
        return try {
            val pageNumber = params.key ?: ""
            val pagePrev = ""
            val response = apiService.getHotRedditPagingPosts(pageNumber, pagePrev)
            val nextKey = response.data.after
            val prevKey = response.data.before
            LoadResult.Page(
                data = response.data.children,
                prevKey = prevKey,
                nextKey = nextKey
            )
        } catch (e: IOException) {
            LoadResult.Error(e)
        } catch (e: HttpException) {
            LoadResult.Error(e)
        }
    }
}