package com.albums.cleanarchitecture.store.networkadapter
import android.webkit.URLUtil
import com.albums.cleanarchitecture.store.networkadapter.IRequestValidator

class RequestValidator : IRequestValidator {
    override fun isValidUrl(url: String): Boolean {
        return URLUtil.isValidUrl(url)
    }

    override fun isValidBody(body: Any): Boolean {
        return true
    }
}