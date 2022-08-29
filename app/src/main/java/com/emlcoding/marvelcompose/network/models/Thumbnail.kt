package com.emlcoding.marvelcompose.network.models

data class Thumbnail(
    val extension: String,
    val path: String
)

// Funcion de extension
fun Thumbnail.asString() = "$path.$extension".replace("http", "https")