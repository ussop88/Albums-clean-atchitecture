package com.albums.cleanarchitecture.store.networkadapter

import com.albums.cleanarchitecture.store.networkadapter.IRequestEncoder

class RequestEncoder : IRequestEncoder {
    override fun encode(body: Any): String {
        try {

        } catch (exception: java.lang.Exception) {
            return ""
        }
        return body.toString()
    }
}