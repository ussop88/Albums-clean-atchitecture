package com.albums.cleanarchitecture.store.networkadapter

import com.albums.store.IHttpRequestBuilder
import com.albums.store.IModel
import com.albums.store.INetworkAdapter
import com.albums.cleanarchitecture.store.data.ExceptionStore
import com.albums.cleanarchitecture.store.data.NetworkServiceError

class NetworkAdapter(
    private val builder: IHttpRequestBuilder,
    private val network: NetworkService
) : INetworkAdapter {

    override fun send(
        hostname: String,
        path: String?,
        headers: Map<String, String>?,
        body: Any?, completionSuccess: (List<IModel>) -> Unit, completionFailure: (NetworkServiceError) -> Unit
    ) {
        Thread(Runnable {
            try {
                val request = builder.buildRequest(hostname, path, headers, body)
                network.request(request, { success ->
                    completionSuccess.invoke(success)
                }) { error ->
                    completionFailure.invoke(error)
                }
            } catch (exception: ExceptionStore) {
                completionFailure.invoke(exception)
            }
        }).start()
    }
}