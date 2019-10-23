package com.albums.store

import com.albums.cleanarchitecture.store.networkadapter.IResponseAdapterFailure
import com.albums.cleanarchitecture.store.networkadapter.NetworkAdapter
import com.albums.cleanarchitecture.store.networkadapter.NetworkService
import com.albums.cleanarchitecture.store.data.NotFoundError
import com.albums.cleanarchitecture.store.data.NetworkServiceError
import com.nhaarman.mockito_kotlin.*
import org.junit.Test
import java.lang.Exception

class NetworkAdapterTest {

    @Test
    fun `test a network adapter when user ask for resources and there is a success when creating request it should send request to network service`() {
        val model: IModel = mock()
        val hostname = "https://static.leboncoin.fr"
        val network = mock<NetworkService>()
        val builder = mock<IHttpRequestBuilder>()
        val networkAdapter: INetworkAdapter =
            NetworkAdapter(
                model,
                builder = builder,
                network = network
            )
        whenever(builder.buildRequest(hostname)).thenReturn(HttpRequest(hostname))

        networkAdapter.send(hostname, completionFailure = mock(), completionSuccess = mock())

        verify(network).request(any(), any(), any(), any())
    }

    @Test(expected = Exception::class)
    fun `test a network adapter when user ask for resources and there is an error when creating request it should send an error`() {
        val model: IModel = mock()
        val hostname = "https://static.leboncoin.fr"
        val builder = mock<IHttpRequestBuilder>()
        val network = mock<NetworkService>()
        val responseAdapterFailure: IResponseAdapterFailure = mock()
        val networkAdapter: INetworkAdapter =
            NetworkAdapter(
                model,
                network = network,
                builder = builder
            )

        networkAdapter.send(hostname, completionFailure = mock(), completionSuccess = mock())

        verify(responseAdapterFailure.failure())
    }

    @Test
    fun `test a network adapter when user ask for resources and there is success it should notify success`() {
        val model: IModel = mock()
        val models = arrayListOf<IModel>()
        val hostname = "https://static.leboncoin.fr"
        val builder = mock<IHttpRequestBuilder>()
        val request = HttpRequest(hostname)
        val network = mock<NetworkService>()
        var completionSuccess = mock<(List<IModel>) -> Unit>()
        val completionFailure = mock<(NetworkServiceError) -> Unit>()
        val networkAdapter: INetworkAdapter =
            NetworkAdapter(
                model,
                network = network,
                builder = builder
            )
        whenever(builder.buildRequest(hostname)).thenReturn(request)
        whenever(
            network.request(
                request = anyOrNull(),
                completionSuccess = anyOrNull(),
                model = anyOrNull(),
                completionFailure = anyOrNull()
            )
        ).doAnswer {
            completionSuccess.invoke(models)
        }
        networkAdapter.send(hostname, completionSuccess = completionSuccess, completionFailure = completionFailure)

        verify(completionSuccess).invoke(models)
        verify(completionFailure, never()).invoke(any())
    }


    @Test
    fun `test a network adapter when a user ask for resource and there is a failure it should notify an error`() {
        val model: IModel = mock()
        val hostname = "https://static.leboncoin.fr"
        val builder = mock<IHttpRequestBuilder>()
        val network = mock<NetworkService>()
        val request = HttpRequest(hostname)
        val notFoundError = NotFoundError(404, "")
        val completionSuccess = mock<(List<IModel>) -> Unit>()
        val completionFailure = mock<(NetworkServiceError) -> Unit>()
        val networkAdapter: INetworkAdapter =
            NetworkAdapter(
                model,
                network = network,
                builder = builder
            )
        whenever(builder.buildRequest(hostname)).thenReturn(request)

        whenever(
            network.request(
                request = anyOrNull(),
                completionSuccess = anyOrNull(),
                model = anyOrNull(),
                completionFailure = anyOrNull()
            )
        ).doAnswer {
            completionFailure.invoke(notFoundError)
        }
        networkAdapter.send(hostname, completionFailure = completionFailure, completionSuccess = completionSuccess)

        verify(completionSuccess, never()).invoke(any())
        verify(completionFailure).invoke(any())
    }


}