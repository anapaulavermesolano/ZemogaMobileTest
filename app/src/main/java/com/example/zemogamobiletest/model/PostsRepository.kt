package com.example.zemogamobiletest.model

import com.example.zemogamobiletest.data.OperationCallback

class PostsRepository(private val postDataSource: PostsDataSource) {
    fun getPost(callback: OperationCallback<Post>) {
        postDataSource.getPosts(callback)
    }
}