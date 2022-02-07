package com.example.zemogamobiletest.viewmodel

import com.example.zemogamobiletest.model.Post

sealed class PostViewState {
    data class Loading(val isViewLoading: Boolean): PostViewState()
    data class Error(val onMessageError: String): PostViewState()
    data class AllPost(val post: List<Post>?): PostViewState()
    data class EmptyPosts(val message: String): PostViewState()
}