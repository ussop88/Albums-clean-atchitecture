package com.albums.store

import com.albums.cleanarchitecture.store.networkadapter.IRequestEncoder
import com.albums.cleanarchitecture.store.networkadapter.IRequestValidator

open class HttpRequestBuilder(
    private val requestValidator: IRequestValidator,
    private val requestEncoder: IRequestEncoder
) : IHttpRequestBuilder {

    override fun buildRequest(
        hostname: String,
        path: String?,
        headers: Map<String, String>?,
        body: Any?
    ): HttpRequest {
        val url = if (path.isNullOrEmpty()) hostname else "$hostname/$path"
        return when {
            requestValidator.isValidUrl(url) && headers == null && body == null -> HttpRequest(url)
            requestValidator.isValidUrl(url) && headers != null && body == null -> HttpRequest(url, headers)
            requestValidator.isValidUrl(url) && headers == null && body != null -> processEncoding(body, url, headers)
            requestValidator.isValidUrl(url) && headers != null && body != null -> processEncoding(body, url, headers)
            else -> throw (HttpRequestBuilderException())
        }
    }

    private fun processEncoding(body: Any, url: String, headers: Map<String, String>?): HttpRequest {
        val encodedBody = requestEncoder.encode(body)
        return if (encodedBody.isNullOrEmpty()) {
            throw (HttpRequestBuilderException())
        } else {
            HttpRequest(url, headers, encodedBody)
        }
    }

    class HttpRequestBuilderException : Exception()
}

