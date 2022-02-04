package com.example.zemogamobiletest.model

import com.example.zemogamobiletest.data.OperationCallback

interface PostsDataSource {
    fun getPosts(callback: OperationCallback<Post>)
    fun cancel()
}