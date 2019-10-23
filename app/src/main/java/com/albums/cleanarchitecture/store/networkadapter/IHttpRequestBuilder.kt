package com.albums.store

interface IHttpRequestBuilder {
    fun buildRequest(hostname: String, path: String?= null, headers: Map<String, String>? = null, body : Any? = null): HttpRequest
}
