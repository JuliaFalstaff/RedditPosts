package com.example.redditposts.domain.model

import com.google.gson.annotations.SerializedName

data class Children (
    @SerializedName("data")
        val data: RedditPost
)
