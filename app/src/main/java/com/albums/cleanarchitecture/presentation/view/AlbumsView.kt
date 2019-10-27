package com.albums.cleanarchitecture.presentation.view

import android.content.Context
import android.support.annotation.MainThread
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.albums.album.R
import com.albums.cleanarchitecture.presentation.controlers.IShowAlbumListControler
import com.albums.store.IModel

class AlbumsView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr),
    IAlbumsView {

    var controller: IShowAlbumListControler? = null
    private lateinit var adapter: AlbumAdapter
    val albumList by lazy {
        findViewById<RecyclerView>(R.id.albumList)
    }

    init {
        val view = LayoutInflater.from(context).inflate(R.layout.albums, this, false)
        addView(view)
    }

    @MainThread
    override fun showAlbums(albums: List<IModel>) {
        if(::adapter.isInitialized) {
            adapter.notifyDataSetChanged(albums)
        } else {
            initAdapter(albums)
        }
    }

    private fun initAdapter(albums: List<IModel>) {
        adapter = AlbumAdapter(albums)
        albumList.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        albumList.adapter = adapter
    }

    fun fetchAlbums() {
        controller?.fetchAlbums()
    }

}