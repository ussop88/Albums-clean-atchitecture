package com.albums.store

import org.junit.Test

class RepositoryTests {

    @Test
    fun `test a repository when a user ask for resource it should ask database first if not available ask network service`() {
/*        val john = Contact(42,"John")
        val localPersistentStore = LocalPersistentStore()
        localPersistentStore.save(john)
        val repository = ResourceRepository(localPersistentStore)

        val resource = repository.fetch(Contact::class, 42)

        assert(resource == john)*/
    }

    @Test
    fun `test a repository when one of the services get success it should send success with response`() {
    }

    @Test
    fun `test a repository when all services fail it should send failure with error`() {
    }

    @Test
    fun `test a repository when a resource is requested then it should immediately be returned`() {
/*        val john = Contact(42,"John")
        val localPersistentStore = LocalPersistentStore()
        localPersistentStore.save(john)
        val repository = ResourceRepository(localPersistentStore)

        val resource = repository.fetch(Contact::class, 42)

        assert(resource == john)*/
    }

    @Test
    fun `test a repository when a resource is requested for the first time then it should create a default one`() {
/*        val localPersistentStore = LocalPersistentStore()
        val repository = ResourceRepository(localPersistentStore)

        val resource = repository.fetch(Contact::class, 42)

        assert(resource == Contact(42, ""))*/
    }

}

/*data class Contact(override var identifier: Int) :IModel {

    var name: String = ""

    constructor(identifier: Int, name: String) : this(identifier) {
        this.identifier = identifier
        this.name = name
    }*/


