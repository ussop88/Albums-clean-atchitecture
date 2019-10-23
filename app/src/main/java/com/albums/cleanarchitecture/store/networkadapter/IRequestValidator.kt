package com.albums.cleanarchitecture.store.networkadapter

import com.albums.cleanarchitecture.store.networkadapter.IIRequestValidator

interface IRequestValidator : IIRequestValidator {
    fun isValidUrl(url: String): Boolean
    fun isValidBody(body: Any): Boolean
}