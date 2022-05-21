package com.example.redditposts.presentation.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import com.example.redditposts.AppState
import com.example.redditposts.databinding.ActivityMainBinding
import com.example.redditposts.presentation.ui.adapter.RedditPostAdapter
import kotlinx.coroutines.flow.collect
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
//        viewModel.getLiveData().observe(this, Observer { renderData(it)
//            Log.d("TAG 777", "$it")})
//        viewModel.getData()
    }

    private fun initList() {
        adapter = RedditPostAdapter()
        binding.recyclerViewHotPost.adapter = adapter
    }


    private fun setUpView() {
        lifecycleScope.launch {
            viewModel.getData().collect {
                adapter?.submitData(it)
            }
        }
    }

    private fun renderData(appState: AppState) {
        when(appState) {
            is AppState.Success -> {
                binding.progressBar.visibility = View.INVISIBLE
                Log.d("TAG 777", "${appState.data}")
            }
            is AppState.Loading -> {
                binding.progressBar.visibility = View.VISIBLE
            }
            is AppState.Error -> {
                binding.progressBar.visibility = View.INVISIBLE
                Toast.makeText(this, "TAG 777 error: ${appState.error.message}", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }
}