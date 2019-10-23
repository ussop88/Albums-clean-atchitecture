package com.albums.cleanarchitecture.store.persistenceadapter

import com.albums.cleanarchitecture.store.data.Model
import com.albums.store.IModel
import io.realm.RealmList
import io.realm.RealmObject

class LocalPersistentStore(private val realmDatabase: RealmDatabase = RealmDatabase()) :
    LocalPersistentDatabase {

    override fun isResourceAvailable(): Boolean {
        return !realmDatabase.getAllProducts().isNullOrEmpty()
    }

    override fun saveAlbums(albumsList: List<IModel>) {
        realmDatabase.saveAlbums(convertToRealmModelList(albumsList))
    }

    override fun getAlbums(): List<IModel> {
        realmDatabase.getAllProducts()?.let {
            return convertToAudioList(it)
        }
        return emptyList()
    }

    private fun convertToRealmModelList(albumsList: List<IModel>): RealmList<ModelRealm> {
        val realmList = RealmList<ModelRealm>()

        albumsList.forEach { element ->
            val realmObject = ModelRealm()
            realmObject.albumId = element.albumId
            realmObject.title = element.title
            realmObject.url = element.url
            realmObject.thumbnailUrl = element.thumbnailUrl
            realmList.add(realmObject)
        }
        return realmList
    }

    private fun convertToAudioList(realmList: ArrayList<ModelRealm>): List<IModel> {
        val audioList = arrayListOf<IModel>()
        realmList.forEach { element ->
            val audioModel = Model(
                element.id,
                element.albumId,
                element.title,
                element.url,
                element.thumbnailUrl
            )
            audioList.add(audioModel)
        }
        return audioList
    }

}

open class ModelRealm(
    override var albumId: Int = 0,
    override var title: String = "",
    override var url: String = "",
    override var thumbnailUrl: String = ""
) : IModel, RealmObject() {
    override var id: Int = 0
}

