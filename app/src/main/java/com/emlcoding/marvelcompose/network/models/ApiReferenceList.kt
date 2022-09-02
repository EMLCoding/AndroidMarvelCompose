package com.emlcoding.marvelcompose.network.models

data class ApiReferenceList(
    val available: Int,
    val collectionURI: String,
    val items: List<ApiReference>?,
    val returned: Int
)