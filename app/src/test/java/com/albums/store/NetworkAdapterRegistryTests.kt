package com.albums.store

import org.junit.Test

class NetworkAdapterRegistryTests {

    @Test
    fun `test a NetworkAdapterRegistry when user asks to register an adapter it should should store it`() { }

    @Test
    fun `test a NetworkAdapterRegistry when user asks to register an adapter that already exists it should override it`() { }

    @Test
    fun `test a NetworkAdapterRegistry that contains a model's adapter when user asks for resources it should return it`() { }

    @Test
    fun `test a NetworkAdapterRegistry not containing model's adapter when user asks for resources it should return default REST network adapter`() { }

}