package com.emlcoding.marvelcompose.network.models

data class ApiSeries(
    val available: Int,
    val collectionURI: String,
    val items: List<ApiSerie>,
    val returned: Int
)