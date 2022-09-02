package com.emlcoding.marvelcompose.network.models

data class ApiStories(
    val available: Int,
    val collectionURI: String,
    val items: List<ApiStory>,
    val returned: Int
)