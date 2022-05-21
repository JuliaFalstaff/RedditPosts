package com.example.redditposts.domain.model

import com.google.gson.annotations.SerializedName

data class Root(
    @SerializedName("data")
    val data: Data,
)
