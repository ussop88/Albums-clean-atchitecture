package com.albums.cleanarchitecture.presentation.presenters
import com.albums.store.IModel

interface IShowAlbumsPresenter {
    fun showAlbums(albums: List<IModel>)

}
