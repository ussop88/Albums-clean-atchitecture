package com.albums.cleanarchitecture.presentation.view

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.albums.album.R
import com.albums.cleanarchitecture.presentation.AlbumFactory


class AlbumActivity : AppCompatActivity() {

    private val view by lazy {
        findViewById<AlbumsView>(R.id.albumsView)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_layout)
        AlbumFactory.make(view).fetchAlbums()
    }

}