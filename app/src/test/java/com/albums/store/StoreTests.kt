package com.albums.store

import com.albums.cleanarchitecture.store.data.Model
import com.albums.cleanarchitecture.store.persistenceadapter.LocalPersistentDatabase
import com.albums.cleanarchitecture.store.data.NotFoundError
import com.albums.cleanarchitecture.store.data.NetworkServiceError
import com.nhaarman.mockito_kotlin.*
import org.junit.Assert
import org.junit.Test

class StoreTests {

    @Test
    fun `test a store when a user ask for resource it should ask database first if not available then ask network service`() {
        val localPersistentStore: LocalPersistentDatabase = mock()
        val network: INetworkAdapter = mock()
        val store = Store(network, localPersistentStore)

        whenever(localPersistentStore.isResourceAvailable()).thenReturn(false)
        store.fetchAlbums({},{})

        verify(network).send(
            hostname = anyOrNull(),
            path = anyOrNull(),
            headers = anyOrNull(),
            body = anyOrNull(),
            completionSuccess = anyOrNull(),
            completionFailure = anyOrNull()
        )
    }

    @Test
    fun `test a store when local persistence contains data it should get response`() {
        val albums = arrayListOf(Model(0, 3, "sweet dreams", "", ""))
        val localPersistentStore: LocalPersistentDatabase = mock()
        val store = Store(mock(), localPersistentStore)

        whenever(localPersistentStore.isResourceAvailable()).thenReturn(true)
        whenever(localPersistentStore.getAlbums()).thenReturn(albums)
        store.fetchAlbums({},{})

        Assert.assertEquals(albums, localPersistentStore.getAlbums())
    }

    @Test
    fun `test a store when network service get success it should get response`() {
        val localPersistentStore: LocalPersistentDatabase = mock()
        val albums = arrayListOf(Model(0, 3, "sweet dreams","", ""))
        val networkAdapter: INetworkAdapter = mock()
        val completionSuccess = mock<(List<IModel>) -> Unit>()
        val completionFailure = mock<(NetworkServiceError) -> Unit>()
        val store = Store(networkAdapter, localPersistentStore)
        whenever(localPersistentStore.isResourceAvailable()).thenReturn(false)

        argumentCaptor<(List<IModel>) -> Unit>().apply {
            whenever(networkAdapter.send(anyOrNull(), anyOrNull(), anyOrNull(), anyOrNull(), capture(), anyOrNull())).doAnswer {
                firstValue.invoke(albums)
            }
        }

        store.fetchAlbums(completionSuccess = completionSuccess, completionFailure = completionFailure)
        verify(completionSuccess).invoke(albums)
        verify(completionFailure, never()).invoke(any())
    }

    @Test
    fun `test a store when all services fail it should send failure with error`() {
        val localPersistentStore: LocalPersistentDatabase = mock()
        val notFoundError = NotFoundError(404, "")
        val albums = arrayListOf(Model(0, 3, "sweet dreams", "", ""))
        val networkAdapter: INetworkAdapter = mock()
        val completionSuccess = mock<(List<IModel>) -> Unit>()
        val completionFailure = mock<(NetworkServiceError) -> Unit>()
        val store = Store(networkAdapter, localPersistentStore)
        whenever(localPersistentStore.isResourceAvailable()).thenReturn(false)

        argumentCaptor<(NetworkServiceError) -> Unit>().apply {
            whenever(networkAdapter.send(anyOrNull(), anyOrNull(), anyOrNull(), anyOrNull(), anyOrNull(), capture())).doAnswer {
                firstValue.invoke(notFoundError)
            }
        }

        store.fetchAlbums(completionSuccess = completionSuccess, completionFailure = completionFailure)
        verify(completionFailure).invoke(notFoundError)
        verify(completionSuccess, never()).invoke(any())

    }

    @Test
    fun `test a store when user ask for on detail resouces it should get resou`() {
        val localPersistentStore: LocalPersistentDatabase = mock()
        val notFoundError = NotFoundError(404, "")
    }

}


