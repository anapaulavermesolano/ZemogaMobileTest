package com.example.zemogamobiletest.model

import com.example.zemogamobiletest.data.OperationCallback

interface CommentsDataSource {
    fun getComments(callback: OperationCallback<Comments>)
    fun cancel()
}