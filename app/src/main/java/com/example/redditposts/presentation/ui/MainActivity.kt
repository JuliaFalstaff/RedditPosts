package com.example.redditposts.presentation.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.redditposts.databinding.ActivityMainBinding
import com.example.redditposts.presentation.ui.adapter.RedditPostAdapter
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.core.component.KoinScopeComponent
import org.koin.core.component.createScope
import org.koin.core.component.inject
import org.koin.core.scope.Scope

class MainActivity : AppCompatActivity(), KoinScopeComponent {

    override val scope: Scope by lazy { createScope(this) }
    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!
    private val viewModel: MainViewModel by inject()
    private var adapter: RedditPostAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initList()
        setUpView()
    }

    private fun initList() {
        adapter = RedditPostAdapter()
        binding.recyclerViewHotPost.adapter = adapter
    }

    private fun setUpView() {
        this.lifecycleScope.launch {
            viewModel.getPagingData().collectLatest { pagingData ->
                adapter = RedditPostAdapter()
                binding.recyclerViewHotPost.adapter = adapter
                adapter?.submitData(pagingData)
            }
        }
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }
}