package com.albums.cleanarchitecture.interactors

import com.albums.cleanarchitecture.presentation.presenters.IShowAlbumsPresenter
import com.albums.cleanarchitecture.store.IStore

class Interactor(private val showAlbumsPresenter: IShowAlbumsPresenter, val store: IStore) :
    ResourceAlbumsFetcher {
    override fun fetchAlbums(){
        store.fetchAlbums({
            showAlbumsPresenter.showAlbums(it)
        }, {

        })
    }
}