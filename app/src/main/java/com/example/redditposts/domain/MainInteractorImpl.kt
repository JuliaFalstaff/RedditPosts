package com.example.redditposts.domain

import com.example.redditposts.AppState
import com.example.redditposts.data.model.Root

class MainInteractorImpl(private val repo: IRemoteRepository<List<Root>>): IMainInteractor {
    override suspend fun getHotRedditPost(): AppState {
        return AppState.Success(repo.getHotRedditPosts())
    }

}