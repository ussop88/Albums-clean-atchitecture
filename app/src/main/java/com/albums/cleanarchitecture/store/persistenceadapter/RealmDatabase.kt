package com.albums.cleanarchitecture.store.persistenceadapter

import com.albums.cleanarchitecture.store.data.DatabaseExceptionStore
import io.realm.*
import io.realm.annotations.RealmModule

private const val DB_NAME = "Albums.realm"
private const val DB_VERSION: Long = 1

class RealmDatabase {

    val realmDefaultInstance: Realm
        get() = Realm.getInstance(REALM_CONFIG)


    private var REALM_CONFIG: RealmConfiguration = RealmConfiguration.Builder().name(DB_NAME)
        .schemaVersion(DB_VERSION)
        .modules(AlbumsRealmModule())
        .build()

    init {
        Realm.setDefaultConfiguration(REALM_CONFIG)
    }

    fun getAllProducts(): ArrayList<ModelRealm>? {
        try {
            val audioList = realmDefaultInstance.where(ModelRealm::class.java).findAll()
            val list = ArrayList<ModelRealm>()
            if (audioList != null) {
                list.addAll(realmDefaultInstance.copyFromRealm(audioList))
            }
            return list
        } catch (exception: java.lang.Exception) {
            return null
        } finally {
            realmDefaultInstance.close()
        }
    }

    fun saveAlbums(albumsList: RealmList<ModelRealm>) {
        try {
            realmDefaultInstance.executeTransaction { realm ->
                realm.insertOrUpdate(albumsList)
            }
        } catch (exception: Exception) {
            throw DatabaseExceptionStore(10, "DB Error")
        } finally {
            realmDefaultInstance.close()
        }
    }
}

@RealmModule(classes = [ModelRealm::class])
class AlbumsRealmModule