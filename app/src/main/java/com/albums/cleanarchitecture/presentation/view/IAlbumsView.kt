package com.albums.cleanarchitecture.presentation.view

import com.albums.store.IModel

interface IAlbumsView {
    fun showAlbums(albums: List<IModel>)

}
