package com.albums.cleanarchitecture.presentation.presenters

import com.albums.cleanarchitecture.presentation.view.IAlbumsView
import com.albums.store.IModel

class ShowAlbumsPresenter(val albumsView: IAlbumsView) :
    IShowAlbumsPresenter {
    override fun showAlbums(albums: List<IModel>) {
        albumsView.showAlbums(albums)
    }
}