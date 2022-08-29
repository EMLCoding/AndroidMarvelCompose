package com.emlcoding.marvelcompose.network.models

data class Series(
    val available: Int,
    val collectionURI: String,
    val items: List<Serie>,
    val returned: Int
)