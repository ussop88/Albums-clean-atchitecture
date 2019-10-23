package com.albums.cleanarchitecture.presentation.controlers

import com.albums.cleanarchitecture.interactors.ResourceAlbumsFetcher

class ShowAlbumListController(private val interactor: ResourceAlbumsFetcher) :
    IShowAlbumListControler {
    override fun fetchAlbums() {
        interactor.fetchAlbums()
    }
}