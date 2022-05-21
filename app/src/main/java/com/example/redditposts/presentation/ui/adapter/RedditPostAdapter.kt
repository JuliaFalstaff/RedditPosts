package com.example.redditposts.presentation.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.redditposts.databinding.ItemPostRecyclerViewBinding
import com.example.redditposts.domain.model.Children
import com.example.redditposts.domain.model.RedditPost

class RedditPostAdapter : PagingDataAdapter<Children, RedditPostAdapter.PostViewHolder>(PostDiffCallback) {

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        getItem(position)?.data?.let { holder.bind(it) }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        return PostViewHolder(ItemPostRecyclerViewBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    object PostDiffCallback : DiffUtil.ItemCallback<Children>() {
        override fun areItemsTheSame(oldItem: Children, newItem: Children): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Children, newItem: Children): Boolean {
            return oldItem == newItem
        }
    }

    inner class PostViewHolder(val binding: ItemPostRecyclerViewBinding) :
            RecyclerView.ViewHolder(binding.root) {
        fun bind(item: RedditPost) = with(binding) {
            countOfPostComments.text = item.numComments.toString()
            countOfLikedPost.text = item.totalAwardsReceived.toString()
            postDescriptionTextView.text = item.title
        }
    }
}