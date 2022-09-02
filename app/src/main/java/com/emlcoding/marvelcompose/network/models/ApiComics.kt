package com.emlcoding.marvelcompose.network.models

data class ApiComics(
    val available: Int,
    val collectionURI: String,
    val items: List<ApiComic>,
    val returned: Int
)