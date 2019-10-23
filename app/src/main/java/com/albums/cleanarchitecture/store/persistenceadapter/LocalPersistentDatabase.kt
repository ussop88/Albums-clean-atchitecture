package com.albums.cleanarchitecture.store.persistenceadapter

import com.albums.store.IModel

interface LocalPersistentDatabase {
    fun saveAlbums(albumLists: List<IModel>)
    fun getAlbums(): List<IModel>
    fun isResourceAvailable(): Boolean

}
