package com.emlcoding.marvelcompose.network.models

data class ApiThumbnail(
    val extension: String,
    val path: String
)

// Funcion de extension
fun ApiThumbnail.asString() = "$path.$extension".replace("http", "https")