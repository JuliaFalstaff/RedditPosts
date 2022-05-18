package com.example.redditposts.model.data

import com.google.gson.annotations.SerializedName

data class Root(
    @SerializedName("kind")
    val kind: String,
    @SerializedName("data")
    val data: Data,
    @SerializedName("after")
    val after: String
)
