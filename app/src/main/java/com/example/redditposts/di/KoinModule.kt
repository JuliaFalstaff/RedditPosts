package com.example.redditposts.di

import com.example.redditposts.domain.IRemoteRepository
import com.example.redditposts.domain.MainInteractorImpl
import com.example.redditposts.domain.RemoteRepositoryImpl
import com.example.redditposts.data.model.Root
import com.example.redditposts.data.retrofit.RetrofitImpl
import com.example.redditposts.presentation.ui.MainActivity
import com.example.redditposts.presentation.ui.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val application = module {
    single<IRemoteRepository<Root>> { RemoteRepositoryImpl(RetrofitImpl()) }
}

val mainScreen = module {
    scope<MainActivity> {
        scoped { MainInteractorImpl(repo = get()) }
        viewModel { MainViewModel(interactor = get()) }
    }
}
