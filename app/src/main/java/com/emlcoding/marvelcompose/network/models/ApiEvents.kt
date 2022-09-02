package com.emlcoding.marvelcompose.network.models

data class ApiEvents(
    val available: Int,
    val collectionURI: String,
    val items: List<ApiEvent>,
    val returned: Int
)