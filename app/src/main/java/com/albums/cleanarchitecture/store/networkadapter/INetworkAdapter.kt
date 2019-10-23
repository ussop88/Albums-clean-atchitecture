package com.albums.store

import com.albums.cleanarchitecture.store.data.NetworkServiceError

interface INetworkAdapter {
    fun send(hostname: String, path: String?= null, headers: Map<String, String>? = null, body : Any? = null,
             completionSuccess: (List<IModel>) -> Unit, completionFailure: (NetworkServiceError) -> Unit)
}
