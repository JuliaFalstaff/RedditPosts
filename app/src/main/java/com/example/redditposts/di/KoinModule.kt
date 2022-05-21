package com.example.redditposts.di

import com.example.redditposts.data.retrofit.ApiService
import com.example.redditposts.data.retrofit.RetrofitImpl
import com.example.redditposts.domain.IRemoteRepository
import com.example.redditposts.domain.PostPagingSource
import com.example.redditposts.domain.RemoteRepositoryImpl
import com.example.redditposts.presentation.ui.MainActivity
import com.example.redditposts.presentation.ui.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val application = module {
    single<IRemoteRepository> { RemoteRepositoryImpl(RetrofitImpl().api) }
    single {PostPagingSource(api = RetrofitImpl().api)}
}

val mainScreen = module {
    scope<MainActivity> {
        viewModel { MainViewModel(repo = get()) }
    }
}
