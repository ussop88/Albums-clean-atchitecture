package com.albums.cleanarchitecture.store.networkadapter
import com.albums.store.HttpRequest
import com.albums.store.IModel
import com.albums.cleanarchitecture.store.data.NetworkServiceError

interface NetworkService {
    fun request(request: HttpRequest, model: IModel, completionSuccess: (List<IModel>) -> Unit, completionFailure: (NetworkServiceError) -> Unit)
}
