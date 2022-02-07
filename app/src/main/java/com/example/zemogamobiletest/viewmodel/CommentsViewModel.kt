package com.example.zemogamobiletest.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.zemogamobiletest.data.OperationCallback
import com.example.zemogamobiletest.model.Comments
import com.example.zemogamobiletest.model.CommentsRepository

class CommentsViewModel(private val repository: CommentsRepository): ViewModel() {
    val viewState: LiveData<CommentsViewState>
        get() = _viewState
    private val _viewState = MutableLiveData<CommentsViewState>()

    fun loadComments(postId: Int) {
        _viewState.value = CommentsViewState.Loading(true)
        repository.getComments(object : OperationCallback<Comments> {
            override fun onError(error: String?) {
                _viewState.value = CommentsViewState.Loading(false)
                _viewState.value = CommentsViewState.Error
            }

            override fun onSuccess(data: List<Comments>?) {
                _viewState.value = CommentsViewState.Loading(false)
                if (data.isNullOrEmpty()) {
                    _viewState.value = CommentsViewState.EmptyPosts
                } else {
                    val filterForPostId = data.filter { it.id == postId }
                    _viewState.value = CommentsViewState.FilterComments(filterForPostId)
                }
            }
        })
    }
}