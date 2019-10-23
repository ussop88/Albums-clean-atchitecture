package com.albums.cleanarchitecture

import android.app.Application
import io.realm.Realm

class AlbumApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        Realm.init(this)
    }
}