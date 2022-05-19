package com.example.redditposts.presentation.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.redditposts.AppState
import com.example.redditposts.domain.MainInteractorImpl
import kotlinx.coroutines.*

class MainViewModel(
    private val interactor: MainInteractorImpl,
    private val liveDataToObserve: MutableLiveData<AppState> = MutableLiveData()
): ViewModel() {

    fun getLiveData(): LiveData<AppState> = liveDataToObserve

    private var job: Job? = null

    private val viewModelCoroutineScope = CoroutineScope(
        Dispatchers.IO
                + SupervisorJob()
                + CoroutineExceptionHandler { _, throwable ->
            handleError(throwable)
        })

    private suspend fun startInteractor() {
        liveDataToObserve.postValue(interactor.getHotRedditPost())
    }

    fun getData() {
        liveDataToObserve.postValue(AppState.Loading)
        job?.cancel()
        job = viewModelCoroutineScope.launch {
            startInteractor()
        }
    }

    private fun handleError(error: Throwable) {
        liveDataToObserve.postValue(AppState.Error(error))
    }
}