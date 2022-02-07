package com.example.zemogamobiletest.viewmodel

import com.example.zemogamobiletest.model.Comments

sealed class CommentsViewState {
    object Error: CommentsViewState()
    data class Loading(val isViewLoading: Boolean): CommentsViewState()
    data class FilterComments(val comments: List<Comments>?): CommentsViewState()
    object EmptyPosts: CommentsViewState()
}
