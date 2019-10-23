package com.albums.cleanarchitecture.store.data

class NotFoundError(override val codeError: Int = 404, override val data: String) :
    NetworkServiceError

class ParsingError(override val codeError: Int = 0, override val data: String) :
    NetworkServiceError

class NetworkError(override val codeError: Int = 0, override val data: String) :
    NetworkServiceError
