package com.example.zemogamobiletest.model

import com.example.zemogamobiletest.data.OperationCallback

class CommentsRepository(private val commentsDataSource: CommentsDataSource) {
    fun getComments(callback: OperationCallback<Comments>) {
        commentsDataSource.getComments(callback)
    }
}