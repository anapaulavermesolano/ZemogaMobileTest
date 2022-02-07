package com.example.zemogamobiletest.room

import android.app.Application
import androidx.room.Room

class RoomApp : Application() {

    val room: PostDB = Room
        .databaseBuilder(this, PostDB::class.java, "post")
        .build()
}