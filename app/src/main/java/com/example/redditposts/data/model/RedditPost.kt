package com.example.redditposts.data.model

import com.google.gson.annotations.SerializedName

data class RedditPost (
    @SerializedName("author_fullname")
    val authorFullName: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("num_comments")
    val numComments: Int,
    @SerializedName("total_awards_received")
    val totalAwardsReceived: Int
)
