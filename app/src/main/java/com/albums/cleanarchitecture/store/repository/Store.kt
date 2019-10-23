package com.albums.store

import com.albums.cleanarchitecture.store.IStore
import com.albums.cleanarchitecture.store.persistenceadapter.LocalPersistentDatabase
import com.albums.cleanarchitecture.store.Configuration
import com.albums.cleanarchitecture.store.data.NetworkServiceError

class Store(private val networkAdapter: INetworkAdapter,private val localPersistentDatabase: LocalPersistentDatabase) :
    IStore {

    override fun fetchAlbums(completionSuccess: (List<IModel>) -> Unit, completionFailure: (NetworkServiceError) -> Unit) {
        if(localPersistentDatabase.isResourceAvailable()) {
            completionSuccess.invoke(localPersistentDatabase.getAlbums())
        } else {
            networkAdapter.send(
                Configuration.URL_PARAMETERS.HOSTNAME.value, Configuration.URL_PARAMETERS.PATH.value,
                completionSuccess = { success ->
                    completionSuccess.invoke(success)
                    localPersistentDatabase.saveAlbums(success)
                },
                completionFailure = { error ->
                    completionFailure.invoke(error)
                })
        }
    }
}