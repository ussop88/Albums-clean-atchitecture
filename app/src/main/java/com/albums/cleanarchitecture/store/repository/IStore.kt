package com.albums.cleanarchitecture.store

import com.albums.store.IModel
import com.albums.cleanarchitecture.store.data.NetworkServiceError

interface IStore {
    fun fetchAlbums(completionSuccess: (List<IModel>) -> Unit, completionFailure: (NetworkServiceError) -> Unit)
}
