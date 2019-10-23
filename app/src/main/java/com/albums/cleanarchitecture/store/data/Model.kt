package com.albums.cleanarchitecture.store.data

import com.albums.store.IModel

class Model(
    override var albumId: Int,
    override var id: Int,
    override var title: String,
    override var url: String,
    override var thumbnailUrl: String
) : IModel