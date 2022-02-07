package com.example.zemogamobiletest.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.zemogamobiletest.model.Post

@Database(
    entities = [Post::class],
    version = 1
)
abstract class PostDB: RoomDatabase() {
    abstract fun postDao(): PostDao
}