package com.example.redditposts.domain.model

data class Data(
    val children: List<Children>,
    val after: String,
    val before: String?
)
