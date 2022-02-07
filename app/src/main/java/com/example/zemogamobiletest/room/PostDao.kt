package com.example.zemogamobiletest.room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.zemogamobiletest.model.Post

@Dao
interface PostDao {
    @Query("SELECT * FROM Post")
    suspend fun getAllPost(): LiveData<List<Post>>

    @Query("SELECT * FROM Post WHERE id = :id")
    suspend fun getById(id: Int): Post

    @Update
    suspend fun update(post: Post)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(post: List<Post>)

    @Delete
    suspend fun delete(post: Post)

    @Query("DELETE FROM Post")
    suspend fun deleteAll()
}
