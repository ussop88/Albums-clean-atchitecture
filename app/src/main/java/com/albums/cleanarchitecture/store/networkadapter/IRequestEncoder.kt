package com.albums.cleanarchitecture.store.networkadapter

interface IRequestEncoder {
    fun encode(body: Any): String
}