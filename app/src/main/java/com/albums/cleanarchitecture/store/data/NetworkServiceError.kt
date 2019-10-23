package com.albums.cleanarchitecture.store.data

interface NetworkServiceError {
    val codeError: Int
    val data: String
}

class ExceptionStore(override val codeError: Int,override val data: String) : Exception() ,
    NetworkServiceError
class DatabaseExceptionStore(override val codeError: Int,override val data: String) : Exception() ,
    NetworkServiceError