package com.albums.store

import org.junit.Test


class RestModelUriTests {

    data class ModelTest(val user:String)

    @Test
    fun `test RestModelUriTests and there is a model without path when write uri it should return rest uri`() {
       /* val restMoDelUri = RestMoDelUri()
        val request = restMoDelUri.buildUriFromModel("http://www.google.fr", null, ModelTest::class.java.simpleName)

        assert(request.url == "http://www.google.fr/modeltests")*/
    }

    @Test
    fun `test RestModelUriTests and there is a model with path when write uri it should return rest uri`() {
       /* val restMoDelUri = RestMoDelUri()
        val request = restMoDelUri.buildUriFromModel("http://www.google.fr", "path", ModelTest::class.java.simpleName)

        assert(request.url == "http://www.google.fr/path/modeltests")
 */   }

}