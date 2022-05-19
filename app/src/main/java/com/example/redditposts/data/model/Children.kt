package com.example.redditposts.data.model

import com.google.gson.annotations.SerializedName

data class Children (
    @SerializedName("data")
        val data: RedditPost
)
