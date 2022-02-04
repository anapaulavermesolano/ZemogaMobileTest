package com.example.zemogamobiletest.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.zemogamobiletest.data.OperationCallback
import com.example.zemogamobiletest.model.Post
import com.example.zemogamobiletest.model.PostsRepository

class PostsViewModel(private val repository: PostsRepository) : ViewModel() {

    val viewState: LiveData<PostViewState>
    get() = _viewState
    private val _viewState = MutableLiveData<PostViewState>()

    fun loadPosts() {
        _viewState.value = PostViewState.Loading(true)
        repository.getPost(object : OperationCallback<Post> {
            override fun onError(error: String?) {
                _viewState.value = PostViewState.Loading(false)
                _viewState.value = PostViewState.Error(error.orEmpty())
            }

            override fun onSuccess(data: List<Post>?) {
                _viewState.value = PostViewState.Loading(false)
                if (data.isNullOrEmpty()) {
                    _viewState.value = PostViewState.Error("Lista vacia")
                } else {
                    _viewState.value = PostViewState.AllPost(data)
                }
            }
        })
    }
}