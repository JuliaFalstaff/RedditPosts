package com.example.redditposts.presentation.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.paging.cachedIn
import com.example.redditposts.AppState
import com.example.redditposts.domain.IRemoteRepository
import kotlinx.coroutines.*

class MainViewModel(
        private val repo: IRemoteRepository,
        private val liveDataToObserve: MutableLiveData<AppState> = MutableLiveData(),
) : ViewModel() {

    fun getLiveData(): LiveData<AppState> = liveDataToObserve

    private var job: Job? = null

    private val viewModelCoroutineScope = CoroutineScope(
            Dispatchers.IO
                    + SupervisorJob()
                    + CoroutineExceptionHandler { _, throwable ->
                handleError(throwable)
            })

//    fun getData() {
//        liveDataToObserve.postValue(AppState.Loading)
//        job?.cancel()
//        job = viewModelCoroutineScope.launch {
//            repo.getHotRedditPosts().cachedIn(this)
//        }
//    }

    suspend fun getData() = repo.getHotRedditPosts().cachedIn(viewModelCoroutineScope)

    private fun handleError(error: Throwable) {
        liveDataToObserve.postValue(AppState.Error(error))
    }
}