package com.albums.cleanarchitecture.store.networkadapter

import com.albums.store.IModel

interface IResponseAdapterSuccess {
fun success(response: List<IModel>)
}
