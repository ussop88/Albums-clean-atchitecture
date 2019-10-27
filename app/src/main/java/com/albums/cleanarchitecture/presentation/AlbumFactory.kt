package com.albums.cleanarchitecture.presentation

import com.albums.cleanarchitecture.interactors.Interactor
import com.albums.cleanarchitecture.presentation.controlers.ShowAlbumListController
import com.albums.cleanarchitecture.presentation.presenters.ShowAlbumsPresenter
import com.albums.cleanarchitecture.presentation.view.AlbumsView
import com.albums.cleanarchitecture.store.data.Model
import com.albums.cleanarchitecture.store.networkadapter.NetworkAdapter
import com.albums.cleanarchitecture.store.networkadapter.OkHttpAdapter
import com.albums.cleanarchitecture.store.networkadapter.RequestEncoder
import com.albums.cleanarchitecture.store.networkadapter.RequestValidator
import com.albums.cleanarchitecture.store.persistenceadapter.LocalPersistentStore
import com.albums.store.*

class AlbumFactory {

    companion object {
        fun make(view: AlbumsView): AlbumsView {

            val store = Store(
                NetworkAdapter(
                    HttpRequestBuilder(
                        RequestValidator(),
                        RequestEncoder()
                    ), OkHttpAdapter()
                ), LocalPersistentStore()
            )

            val controler = ShowAlbumListController(
                Interactor(
                    ShowAlbumsPresenter(view), store
                )
            )
            view.controller = controler
            return view
        }
    }
}