package com.example.redditposts.di

import com.example.redditposts.data.PostPagingSource
import com.example.redditposts.data.RemoteRepositoryImpl
import com.example.redditposts.data.retrofit.RetrofitImpl
import com.example.redditposts.domain.IRemoteRepository
import com.example.redditposts.presentation.ui.MainActivity
import com.example.redditposts.presentation.ui.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val application = module {
    single<IRemoteRepository> { RemoteRepositoryImpl(RetrofitImpl().api) }
    single { PostPagingSource(apiService = RetrofitImpl().api) }
}

val mainScreen = module {
    scope<MainActivity> {
        viewModel { MainViewModel(repo = get()) }
    }
}
