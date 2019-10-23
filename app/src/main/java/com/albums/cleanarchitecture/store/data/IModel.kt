package com.albums.store

import java.io.Serializable

interface IModel : Serializable {
    var albumId: Int
    var id: Int
    var title: String
    var url: String
    var thumbnailUrl: String
}