package com.albums.store

data class HttpRequest(
    val url: String,
    val headers: Map<String, String>? = null,
    val body : String? = null
)