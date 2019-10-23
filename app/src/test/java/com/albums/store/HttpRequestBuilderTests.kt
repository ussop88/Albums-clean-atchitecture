package com.albums.store

import com.albums.cleanarchitecture.store.networkadapter.IRequestEncoder
import com.albums.cleanarchitecture.store.networkadapter.IRequestValidator
import com.nhaarman.mockito_kotlin.*
import org.junit.Assert
import org.junit.Test

class HttpRequestBuilderTests {

    @Test
    fun `test a HttpRequestBuilder and there is a valid hostname when user asks for resources it should build request`() {
        val requestValidator = mock<IRequestValidator>()
        val builder = HttpRequestBuilder(requestValidator, mock())

        whenever(requestValidator.isValidUrl(any())).thenReturn(true)
        val request = builder.buildRequest("https://static.leboncoin.fr")

        Assert.assertEquals("https://static.leboncoin.fr", request.url)
    }

    @Test
    fun `test a HttpRequestBuilder and there is a valid path when user asks for resources it should make a url`() {
        val requestValidator = mock<IRequestValidator>()
        val builder = HttpRequestBuilder(requestValidator, mock())

        whenever(requestValidator.isValidUrl(any())).thenReturn(true)
        val request = builder.buildRequest("https://static.leboncoin.fr", "img/shared/technical-test.json")

        Assert.assertEquals("https://static.leboncoin.fr/img/shared/technical-test.json", request.url)
    }

    @Test(expected = HttpRequestBuilder.HttpRequestBuilderException::class)
    fun `test a HttpRequestBuilder and there is invalid url when user asks for resources it should send error`() {
        val requestValidator = mock<IRequestValidator>()
        val builder = HttpRequestBuilder(requestValidator, mock())
        val hostname = "https://static.leboncoin.fr"

        whenever(requestValidator.isValidUrl(any())).thenReturn(false)

        builder.buildRequest(hostname)
    }

    @Test(expected = HttpRequestBuilder.HttpRequestBuilderException::class)
    fun `test a HttpRequestBuilder and there is body encoding problem when user asks for resources it send error`() {
        val requestValidator = mock<IRequestValidator>()
        val requestEncoder = mock<IRequestEncoder>()
        val builder = HttpRequestBuilder(requestValidator, requestEncoder)
        val hostname = "https://static.leboncoin.fr"

        whenever(requestValidator.isValidUrl(any())).thenReturn(true)
        whenever(requestEncoder.encode(any())).thenReturn("")

        builder.buildRequest(hostname, body = Object())
    }

    @Test
    fun `test a HttpRequestBuilder and there is valid url body and headers when user asks for resources it build request`() {
        val requestValidator = mock<IRequestValidator>()
        val requestEncoder: IRequestEncoder = mock()
        val builder = HttpRequestBuilder(requestValidator = requestValidator, requestEncoder = requestEncoder)
        val hostname = "https://static.leboncoin.fr"
        val path = "img/shared/technical-test.json"
        val headers = mapOf<String, String>("ucef" to "mourchid")
        val body = Object()

        whenever(requestEncoder.encode(body)).thenReturn(body.toString())
        whenever(requestValidator.isValidUrl(any())).thenReturn(true)
        val request = builder.buildRequest(hostname, path, headers, body)

        Assert.assertEquals(request.url , "https://static.leboncoin.fr/img/shared/technical-test.json")
        Assert.assertEquals(request.body , body.toString())
        Assert.assertEquals(request.headers , headers)
    }

}

